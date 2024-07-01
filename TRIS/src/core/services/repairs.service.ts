import { BadRequestException, Injectable, NotFoundException } from '@nestjs/common';

import { Connection, DeepPartial } from 'typeorm';

import { Repair } from '../entities/repair.entity';
import { BaseService } from 'src/common/base-service';
import { ReadAllResult } from 'src/common/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';

@Injectable()
export class RepairsService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async create(
        repair: DeepPartial<Repair>,
        options: ITransactionOptions = {},
    ): Promise<Repair> {
        return this.execMethod<Repair>(async queryRunner => {
            const newRepair = queryRunner.manager.create(Repair, repair);
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(Repair)
                .values(newRepair)
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            delete newRepair.deletedAt;

            return newRepair;
        }, options);
    }

    public async readAll(
        options: ITransactionOptions = {},
    ): Promise<ReadAllResult<Repair>> {
        return this.execMethod<ReadAllResult<Repair>>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            queryBuilder
                .select(['repair.id', 'repair.discription', 'repair.begDate', 'repair.endDate'])
                .from(Repair, 'repair')
                .leftJoin('repair.car', 'car')
                .leftJoin('repair.user', 'user')
                .addSelect([
                    'car.id',
                    'car.mark',
                    'car.model',
                    'user.id',
                    'user.login',
                ]);

            const result = await queryBuilder.getManyAndCount();

            return {
                totalRecordsNumber: result[1],
                entities: result[0],
            };
        }, options);
    }

    public async readById(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<Repair> {
        return this.execMethod<Repair>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            const repair = await queryBuilder
                .select(['repair.id', 'repair.discription', 'repair.begDate', 'repair.endDate'])
                .from(Repair, 'repair')
                .leftJoin('repair.car', 'car')
                .leftJoin('repair.user', 'user')
                .addSelect([
                    'car.id',
                    'car.mark',
                    'car.model',
                    'user.id',
                    'user.login',
                ])
                .where('repair.id = :id', { id })
                .getOne();

            if(!repair) {
                throw new NotFoundException('Repair does not exist');
            }

            return repair;
        }, options);
    }

    public async update(
        id: number,
        newRepair: DeepPartial<Repair>,
        options: ITransactionOptions = {},
    ): Promise<Repair> {
        return this.execMethod<Repair>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Repair, { queryRunner });

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .update(Repair)
                .set(newRepair)
                .where('id = :id', {id })
                .execute();

            const updatedRepair = await this.readById(id, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return updatedRepair;
        }, options);
    }

    public async delete(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Repair, { queryRunner });

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .softDelete()
                .from(Repair)
                .where('id = :id', { id })
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public createRepairEntityFrom(plainObject: DeepPartial<Repair>): Repair {
        return this.connection.manager.create(Repair, plainObject);
    }
}