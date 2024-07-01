import { BadRequestException, Injectable, NotFoundException } from '@nestjs/common';

import { Connection, DeepPartial } from 'typeorm';

import { BaseService } from 'src/common/base-service';
import { ReadAllResult } from 'src/common/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';
import { Role } from '../entities/role.entity';

@Injectable()
export class RolesService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async create(
        role: DeepPartial<Role>,
        options: ITransactionOptions = {},
    ): Promise<Role> {
        return this.execMethod<Role>(async queryRunner => {
            const existingRole = await this._readByName(
                role.name,
                { queryRunner },
            );

            if(existingRole) {
                throw new BadRequestException(`Role ${role.name} is already exist`);
            }

            const newRole = queryRunner.manager.create(Role, role);
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(Role)
                .values(newRole)
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            delete newRole.deletedAt;

            return newRole;
        }, options);
    }

    public async readAll(
        options: ITransactionOptions = {},
    ): Promise<ReadAllResult<Role>> {
        return this.execMethod<ReadAllResult<Role>>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            queryBuilder
                .select(['role.id', 'role.name'])
                .from(Role, 'role');

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
    ): Promise<Role> {
        return this.execMethod<Role>(async queryRunner => {
            const role = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select(['role.id', 'role.name'])
                .from(Role, 'role')
                .where('role.id = :id', { id })
                .getOne();

            if(!role) {
                throw new NotFoundException('Role not found');
            }

            return role;
        }, options);
    }

    private async _readByName(
        name: string,
        options: ITransactionOptions = {},
    ): Promise<Role> {
        return this.execMethod<Role>(async queryRunner => {
            const role = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select('role.id')
                .from(Role, 'role')
                .where('role.name = :name', { name })
                .getOne();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return role;
        }, options)
    }

    public async update(
        id: number,
        newRole: DeepPartial<Role>,
        options: ITransactionOptions = {},
    ): Promise<Role> {
        return this.execMethod<Role>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Role, { queryRunner });

            const existingRole = await this._readByName(
                newRole.name,
                { queryRunner },
            );

            if(existingRole && existingRole.id !== id) {
                throw new BadRequestException(`Role ${newRole.name} is already exist`);
            }

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .update(Role)
                .set(newRole)
                .where('id = :id', { id })
                .execute();

            const updatedRole = await this.readById(id, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return updatedRole;
        }, options);
    }

    public async delete(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, Role, { queryRunner });

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .softDelete()
                .from(Role)
                .where('id = :id', { id })
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public createRoleEntityFrom(plainObject: DeepPartial<Role>): Role {
        return this.connection.manager.create(Role, plainObject);
    }
}