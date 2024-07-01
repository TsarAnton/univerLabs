import { BadRequestException, Injectable, NotFoundException } from '@nestjs/common';

import { Connection, DeepPartial } from 'typeorm';

import { BaseService } from 'src/common/base-service';
import { ReadAllResult } from 'src/common/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';
import { Route } from '../entities/route.entity';

@Injectable()
export class RoutesService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async create(
        route: DeepPartial<Route>,
        options: ITransactionOptions = {},
    ): Promise<Route> {
        return this.execMethod<Route>(async queryRunner => {
            const existingRoute = await this._readByBegPointAndEndPoint(
                route.begPoint,
                route.endPoint,
                { queryRunner },
            );

            if(existingRoute) {
                throw new BadRequestException(`Route ${route.begPoint}-${route.endPoint} is already exist`);
            }

            const newRoute = queryRunner.manager.create(Route, route);
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(Route)
                .values(newRoute)
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            delete newRoute.deletedAt;

            return newRoute;
        }, options);
    }

    public async readAll(
        options: ITransactionOptions = {},
    ): Promise<ReadAllResult<Route>> {
        return this.execMethod<ReadAllResult<Route>>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            queryBuilder
                .select(['route.id', 'route.begPoint', 'route.endPoint'])
                .from(Route, 'route');

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
    ): Promise<Route> {
        return this.execMethod<Route>(async queryRunner => {
            const route = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select(['route.id', 'route.begPoint', 'route.endPoint'])
                .from(Route, 'route')
                .where('route.id = :id', { id })
                .getOne();

            if(!route) {
                throw new NotFoundException('Route not found');
            }

            return route;
        }, options);
    }

    private async _readByBegPointAndEndPoint(
        begPoint: string,
        endPoint: string,
        options: ITransactionOptions = {},
    ): Promise<Route> {
        return this.execMethod<Route>(async queryRunner => {
            const route = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select('route.id')
                .from(Route, 'route')
                .where('route.begPoint = :begPoint', {begPoint })
                .andWhere('route.endPoint = :endPoint', { endPoint })
                .getOne();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return route;
        }, options)
    }

    public async update(
        id: number,
        newRoute: DeepPartial<Route>,
        options: ITransactionOptions = {},
    ): Promise<Route> {
        return this.execMethod<Route>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Route, { queryRunner });

            const existingRoute = await this._readByBegPointAndEndPoint(
                newRoute.begPoint,
                newRoute.endPoint,
                { queryRunner },
            );

            if(existingRoute && existingRoute.id !== id) {
                throw new BadRequestException(`Route ${newRoute.begPoint}-${newRoute.endPoint} is already exist`);
            }

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .update(Route)
                .set(newRoute)
                .where('id = :id', { id })
                .execute();

            const updatedRoute = await this.readById(id, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return updatedRoute;
        }, options);
    }

    public async delete(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Route, { queryRunner });

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .softDelete()
                .from(Route)
                .where('route.id = :id', { id })
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public createRouteEntityFrom(plainObject: DeepPartial<Route>): Route {
        return this.connection.manager.create(Route, plainObject);
    }
}