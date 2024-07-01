import { BadRequestException, Injectable, NotFoundException } from '@nestjs/common';

import { Connection, DeepPartial } from 'typeorm';

import { BaseService } from 'src/common/base-service';
import { ReadAllResult } from 'src/common/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';
import { Car } from '../entities/car.entity';

@Injectable()
export class CarsService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async create(
        car: DeepPartial<Car>,
        options: ITransactionOptions = {},
    ): Promise<Car> {
        return this.execMethod<Car>(async queryRunner => {
            const existingCar = await this._readByMarkAndModel(
                car.mark,
                car.model,
                { queryRunner },
            );

            if(existingCar) {
                throw new BadRequestException(`Car ${car.mark} ${car.model} is already exist`);
            }

            const newCar = queryRunner.manager.create(Car, car);
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(Car)
                .values(newCar)
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            delete newCar.deletedAt;

            return newCar;
        }, options);
    }

    public async readAll(
        options: ITransactionOptions = {},
    ): Promise<ReadAllResult<Car>> {
        return this.execMethod<ReadAllResult<Car>>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            queryBuilder
                .select(['car.id', 'car.mark', 'car.model'])
                .from(Car, 'car');

            const result = await queryBuilder.getManyAndCount();

            return {
                totalRecordsNumber: result[1],
                entities: result[0],
            };
        }, options)
    }

    public async readById(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<Car> {
        return this.execMethod<Car>(async queryRunner => {
            const car = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select(['car.id', 'car.mark', 'car.model'])
                .from(Car, 'car')
                .where('car.id = :id', { id })
                .getOne();

            if(!car) {
                throw new NotFoundException('Car not found');
            }

            return car;
        }, options);
    }

    private async _readByMarkAndModel(
        mark: string,
        model: string,
        options: ITransactionOptions = {},
    ): Promise<Car> {
        return this.execMethod<Car>(async queryRunner => {
            const car = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select('car.id')
                .from(Car, 'car')
                .where('car.mark = :mark', {mark })
                .andWhere('car.model = :model', { model })
                .getOne();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return car;
        }, options)
    }

    public async update(
        id: number,
        newCar: DeepPartial<Car>,
        options: ITransactionOptions = {},
    ): Promise<Car> {
        return this.execMethod<Car>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Car, { queryRunner });

            const existingCar = await this._readByMarkAndModel(
                newCar.mark,
                newCar.model,
                { queryRunner },
            );

            if(existingCar && existingCar.id !== id) {
                throw new BadRequestException(`Car ${newCar.mark} ${newCar.model} is already exist`);
            }

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .update(Car)
                .set(newCar)
                .where('id = :id', { id })
                .execute();

            const updatedCar = await this.readById(id, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return updatedCar;
        }, options);
    }

    public async delete(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Car, { queryRunner });

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .softDelete()
                .from(Car)
                .where('id = :id', { id })
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public createCarEntityFrom(plainObject: DeepPartial<Car>): Car {
        return this.connection.manager.create(Car, plainObject);
    }
}