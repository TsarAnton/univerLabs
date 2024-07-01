import { BadRequestException, Injectable, NotFoundException } from '@nestjs/common';
import { BaseService } from 'src/common/base-service';
import { ReadAllResult } from 'src/common/base-service.types';
import { ITransactionOptions } from 'src/types/transaction-options';

import { Connection, DeepPartial } from 'typeorm';

import { User } from '../entities/user.entity';

@Injectable()
export class UsersService extends BaseService {
    constructor(protected connection: Connection) {
        super(connection);
    }

    public async create(
        user: DeepPartial<User>, 
        options: ITransactionOptions = {},
    ): Promise<User> {
        return this.execMethod<User>(async queryRunner => {
            const existingUser = await this._readByLogin(user.login, { queryRunner });

            if(existingUser) {
                throw new BadRequestException(`User ${user.login} is already exist`);
            }
            const newUser = queryRunner.manager.create(User, user);
            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .insert()
                .into(User)
                .values(newUser)
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return newUser;
        }, options);
    }

    public async readAll(
        options: ITransactionOptions = {},
    ): Promise<ReadAllResult<User>> {
        return this.execMethod<ReadAllResult<User>>(async queryRunner => {
            const queryBuilder = queryRunner.manager.createQueryBuilder(queryRunner);

            queryBuilder
                .select(['user.id', 'user.login'])
                .from(User, 'user');

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
    ): Promise<User> {
        return this.execMethod<User>(async queryRunner => {
            const user = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select(['user.id', 'user.login'])
                .from(User, 'user')
                .where('user.id = :id', { id })
                .getOne();

            if(!user) {
                throw new NotFoundException('User not found');
            }

            return user;
        }, options);
    }

    private async _readByLogin(
        login: string,
        options: ITransactionOptions = {},
    ): Promise<User> {
        return this.execMethod<User>(async queryRunner => {
            const user = await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .select(['user.id', 'user.login'])
                .from(User, 'user')
                .where('user.login = :login', { login })
                .getOne();

            if(!user) {
                throw new NotFoundException('User not found');
            }

            return user;
        }, options);
    }

    public async update(
        id: number,
        newUser: DeepPartial<User>,
        options: ITransactionOptions = {},
    ): Promise<User> {
        return this.execMethod<User>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, User, { queryRunner });
            
            const existingUser = await this._readByLogin(newUser.login, { queryRunner });

            if(existingUser && existingUser.id !== id) {
                throw new BadRequestException(`User ${newUser.login} is already exist`);
            }

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .update(User)
                .set(newUser)
                .where('id = :id', { id })
                .execute();

            const updatedUser = await  this.readById(id, { queryRunner });

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }

            return updatedUser;
        }, options)
    }


    public async delete(
        id: number,
        options: ITransactionOptions = {},
    ): Promise<void> {
        return this.execMethod<void>(async queryRunner => {
            await this._throwErrorIfEntityDoesNotExist(id, User, { queryRunner });

            await queryRunner.manager
                .createQueryBuilder(queryRunner)
                .softDelete()
                .from(User)
                .where('id = :id', { id })
                .execute();

            if(!options.queryRunner) {
                await queryRunner.commitTransaction();
            }
        }, options);
    }

    public createUserEntityFrom(plainObject: DeepPartial<User>): User {
        return this.connection.manager.create(User, plainObject);
    }
}