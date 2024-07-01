import { BadRequestException, Injectable } from '@nestjs/common';

import { Connection, DeepPartial } from 'typeorm';

import { BaseService } from 'src/common/base-service';
import { ITransactionOptions } from 'src/types/transaction-options';
import { User } from '../entities/user.entity';
import { Role } from '../entities/role.entity';
import { UserToRole } from '../entities/user-to-role.entity';

@Injectable()
export class UsersToRolesService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async createRolesForUser(
        user: DeepPartial<User>,
        roles: DeepPartial<Role[]>,
        options: ITransactionOptions = {},
    ): Promise<UserToRole[]> {
        return this.execMethod<UserToRole[]>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(user.id, User, {
                queryRunner,
                errorMessage: `User does not exist`,
            });

            const userToRoles = this._createRolesForUser(user, roles, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return userToRoles;
        }, options);
    }

    private async _createRolesForUser(
        user: DeepPartial<User>,
        roles: DeepPartial<Role[]>,
        options: ITransactionOptions = {},
    ): Promise<UserToRole[]> {
        return this.execMethod<UserToRole[]>(async queryRunner => {
            if(roles.length) {
            const existingUserToRole = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select('userToRole.id')
                .from(UserToRole, 'userToRole')
                .where('userToRole.user_id = :userId', { userId: user.id })
                .andWhere('userToRole.role_id IN (:...roleIds)', { roleIds: roles.map(role => role.id) })
                .getOne();

            if(existingUserToRole) {
                throw new BadRequestException('One or more roles have already been created for this user');
            }

            const newUsersToRoles = roles.map(role => this.createUserToRoleEntityFrom({ user, role}));

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(UserToRole)
                .values(newUsersToRoles)
                .execute();

            return newUsersToRoles;
        }

        if(!options.queryRunner) {
            await queryRunner.commitTransaction();
        }
        }, options);
    }

    public async deleteRolesForUser(
        user: DeepPartial<User>,
        roles: DeepPartial<Role[]>,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(user.id, User, {
                queryRunner,
                errorMessage: `User does not exist`,
            });

            this._deleteRolesForUser(user, roles, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    private async _deleteRolesForUser(
        user: DeepPartial<User>,
        roles: DeepPartial<Role[]>,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            if(roles.length) {
                await queryRunner.manager
                    .createQueryBuilder(queryRunner)
                    .softDelete()
                    .from(UserToRole)
                    .where('user_id = :userId', { userId: user.id })
                    .andWhere('role_id IN (:...roleIds)', {
                        roleIds: roles.map(role => role.id),
                    })
                    .execute();
            }

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public async setRolesForUser(
        user: DeepPartial<User>,
        roles: DeepPartial<Role[]>,
        options: ITransactionOptions = {},
    ): Promise<UserToRole[]> {
        return this.execMethod<UserToRole[]>(async queryRunner => {
            if(roles.length === 0) {
                throw new BadRequestException('User must have at least one role');
            }

            await this._throwErrorIfEntityDoesNotExist(user.id, User, {
                queryRunner,
                errorMessage: `User does not exist`,
            });

            const allRoles = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select('role.id')
                .from(Role, 'role')
                .getMany();
            
            await this._deleteRolesForUser(user, allRoles, { queryRunner });
            const userToRoles = await this._createRolesForUser(user, roles, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return userToRoles;
        }, options);
    }

    public createUserToRoleEntityFrom(
        plainObject: DeepPartial<UserToRole>,
    ):UserToRole {
        return this.connection.manager.create(UserToRole, plainObject);
    }
}