import { BadRequestException, Injectable, NotFoundException } from '@nestjs/common';

import { Connection, DeepPartial } from 'typeorm';

import { RouteList } from '../entities/route-list.entity';
import { BaseService } from 'src/common/base-service';
import { ReadAllResult } from 'src/common/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';

@Injectable()
export class RouteListsService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async create(
        routeList: DeepPartial<RouteList>,
        options: ITransactionOptions = {},
    ): Promise<RouteList> {
        return this.execMethod<RouteList>(async queryRunner => {
            const newRouteList = queryRunner.manager.create(RouteList, routeList);

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(RouteList)
                .values(newRouteList)
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            delete newRouteList.deletedAt;

            return newRouteList;
        }, options);
    }

    public async readAll(
        options: ITransactionOptions = {},
    ): Promise<ReadAllResult<RouteList>> {
        return this.execMethod<ReadAllResult<RouteList>>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            queryBuilder
                .select(['routeList.id', 'routeList.isEnded'])
                .from(RouteList, 'routeList')
                .leftJoin('routeList.car', 'car')
                .leftJoin('routeList.driver', 'user')
                .leftJoin('routeList.dispatcher', 'user')
                .leftJoin('routeList.route', 'route')
                .addSelect([
                    'car.id',
                    'car.mark',
                    'car.model',
                    'driver.id',
                    'driver.login',
                    'dispatcher.id',
                    'dispatcher.login',
                    'route.id',
                    'route.begPoint',
                    'route.endPoint',
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
    ): Promise<RouteList> {
        return this.execMethod<RouteList>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            const routeList = await queryBuilder
                .select(['routeList.id', 'routeList.isEnded'])
                .from(RouteList, 'routeList')
                .leftJoin('routeList.car', 'car')
                .leftJoin('routeList.driver', 'user')
                .leftJoin('routeList.dispatcher', 'user')
                .leftJoin('routeList.route', 'route')
                .addSelect([
                    'car.id',
                    'car.mark',
                    'car.model',
                    'driver.id',
                    'driver.login',
                    'dispatcher.id',
                    'dispatcher.login',
                    'route.id',
                    'route.begPoint',
                    'route.endPoint',
                ])
                .where('routeList.id = :id', { id })
                .getOne();

                if(!routeList) {
                    throw new NotFoundException('Route-list does not exist');
                }

                return routeList;
        }, options);
    }

    public async update(
        id: number,
        newRouteList: DeepPartial<RouteList>,
        options: ITransactionOptions = {},
    ): Promise<RouteList> {
        return this.execMethod<RouteList>(async queryRunner => {
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .update(RouteList)
                .set(newRouteList)
                .where('id = :id', { id })
                .execute();

            const updatedRouteList = await this.readById(id, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return updatedRouteList;
        }, options);
    }

    public async delete(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .softDelete()
                .from(RouteList)
                .where('id = :id', { id })
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public createRouteListEntityFrom(plainObject: DeepPartial<RouteList>): RouteList {
        return this.connection.manager.create(RouteList, plainObject);
    }
}