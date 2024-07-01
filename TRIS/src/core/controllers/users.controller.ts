import {
	Controller,
	Post,
	Get,
	Delete,
	Param,
	Body,
	HttpCode,
	Patch,
	UseGuards,
	Query,
	HttpStatus,
} from '@nestjs/common';

import { Connection } from 'typeorm';

import { BaseController } from 'src/common/base-controller';
import { ReadAllResult } from 'src/common/base-service.types';
import { UsersService } from '../services/users.service';
import { UsersToRolesService } from '../services/users-to-roles.service';
import { RolesService } from '../services/roles.service';
import { CreateUserDto } from '../dto/user.dto';
import { UpdateUserDto } from '../dto/user.dto';
import { FrontendUser } from '../types/users.types';

@Controller('users')
export class UsersController extends BaseController {
	constructor(
		protected connection: Connection,
		private usersService: UsersService,
		private usersToRolesService: UsersToRolesService,
		private rolesService: RolesService,
	) {
		super(connection);
	}

	@Post()
	@HttpCode(HttpStatus.CREATED)
	public async create(@Body() createUserDto: CreateUserDto
	): Promise<FrontendUser> {
		return this.execInTransaction<FrontendUser>(async queryRunner => {
			const userEntity = this.usersService.createUserEntityFrom({
				login: createUserDto.login,
				password: createUserDto.password,
			});
			const createdUser = await this.usersService.create(userEntity, { queryRunner });

			const allRoleEntities = await this.rolesService.readAll({ queryRunner });

			const roleEntities = allRoleEntities.entities.filter(roleEntity => {
				return createUserDto.roleIds.includes(roleEntity.id);
			});
			const createdUserToRoles = await this.usersToRolesService.createRolesForUser(
				createdUser,
				roleEntities,
				{ queryRunner },
			)

			createdUser.userToRoles = createdUserToRoles;

			return new FrontendUser(createdUser);
		});
	}

	@Get()
	public async readAll(): Promise<ReadAllResult<FrontendUser>> {
		const users = await this.usersService.readAll();

		return {
			totalRecordsNumber: users.totalRecordsNumber,
			entities: users.entities.map(user => new FrontendUser(user)),
		};
	}

	@Get(':id')
	public async update(
		@Param('id') id: number,
		@Body() updateUserDto: UpdateUserDto,
	): Promise<FrontendUser> {
		return this.execInTransaction<FrontendUser>(async queryRunner => {
			const updatedUser = await this.usersService.readById(id, { queryRunner });

			const allroleEntities = await this.rolesService.readAll({ queryRunner });

			const roleEntities = allroleEntities.entities.filter(roleEntity => {
				return updateUserDto.roleIds.includes(roleEntity.id);
			});
			const updatedUserToRoles = await this.usersToRolesService.setRolesForUser(
				updatedUser,
				roleEntities,
				{ queryRunner },
			);

			updatedUser.userToRoles = updatedUserToRoles;

			return new FrontendUser(updatedUser);
		});
	}

	@Delete(':id')
	@HttpCode(HttpStatus.NO_CONTENT)
	public async delete(@Param('id') id: number): Promise<void> {
		return this.execInTransaction<void>(async queryRunner => {
			const allRoleEntities = await this.rolesService.readAll({ queryRunner });

			await this.usersToRolesService.deleteRolesForUser({ id }, allRoleEntities.entities, { queryRunner });

			await this.usersService.delete(id, { queryRunner });
		});
	}
}