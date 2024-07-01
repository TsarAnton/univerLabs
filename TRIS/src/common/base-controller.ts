import { HttpException, InternalServerErrorException } from '@nestjs/common';

import { Connection, QueryRunner } from 'typeorm';

export type ControllerMethod<T> = (queryRunner: QueryRunner) => Promise<T>;

export abstract class BaseController {
    constructor(protected connection: Connection) {};

    protected async execInTransaction<T>(method: ControllerMethod<T>): Promise<T> {
        const queryRunner = this.connection.createQueryRunner();

        try {
            await queryRunner.connect();
            await queryRunner.startTransaction();

            const result = await method(queryRunner);

            await queryRunner.commitTransaction();

            return result;
        } catch(error) {
            if(queryRunner.isTransactionActive) {
                await queryRunner.rollbackTransaction();
            }

            if(error instanceof HttpException) {
                throw error;
            } else {
                const internalServerError = {
                    ...new InternalServerErrorException(error.message),
                    stack: error.stack,
                };

                throw internalServerError;
            }
        } finally {
            if(queryRunner.isTransactionActive) {
                await queryRunner.release();
            }
        }
    }
}