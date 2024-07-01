import { HttpException, InternalServerErrorException, NotFoundException } from '@nestjs/common';

import { Connection, QueryRunner, ObjectType, ObjectLiteral, QueryBuilder } from 'typeorm';

import { ServiceMethod } from 'src/types/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';
import { IThrowErrorMethodOptions } from 'src/types/base-service.types';

export abstract class BaseService {
    constructor(protected connection: Connection) {}

    protected async execMethod<T>(
        method: ServiceMethod<T>,
        options: ITransactionOptions = {},
    ): Promise<T> {
        let queryRunner = options.queryRunner;

        if(options.queryRunner) {
            queryRunner = options.queryRunner;
        } else {
            queryRunner = this.connection.createQueryRunner();

            await queryRunner.connect();
            await queryRunner.startTransaction();
        }

        try {
            return await method(queryRunner, options);
        } catch(error) {
            if(queryRunner.isTransactionActive) {
                await queryRunner.rollbackTransaction();
            }

            if(error instanceof HttpException) {
                throw error;
            } else {
                const InternalServerError = {
                    ...new InternalServerErrorException(error.message),
                    stack: error.stack,
                };

                throw InternalServerError;
            }
        } finally {
            if(!options.queryRunner && queryRunner.isTransactionActive) {
                await queryRunner.release();
            }
        }
    }

    protected async _throwErrorIfEntityDoesNotExist<T>(
        id: number,
        entityTarget: ObjectType<T>,
        options: IThrowErrorMethodOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            const permission = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select('entity.id')
                .from(entityTarget, 'entity')
                .where('entity.id = :id', { id })
                .getOne();

            if(!permission) {
                if(options.errorMessage) {
                    throw new NotFoundException(options.errorMessage);
                }

                throw new NotFoundException(`${entityTarget.name} not found`);
            }

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }
}