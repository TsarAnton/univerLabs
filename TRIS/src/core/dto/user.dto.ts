import {
	IsArray,
	IsDate,
	IsDefined,
	IsEmail,
	IsIn,
	IsInt,
	IsNotEmpty,
	IsOptional,
	IsString,
	MaxLength,
} from 'class-validator';
import { Transform } from 'class-transformer';

export class CreateUserDto {
	@IsNotEmpty()
	@MaxLength(255)
	@IsString()
	login: string;

	@IsNotEmpty()
	@MaxLength(100)
	@IsString()
	password: string;

	@IsDefined()
	@IsArray()
	@IsInt({ each: true })
	@Transform((roleIds: string[]) => roleIds.map(id => Number(id)))
	roleIds: number[];
}

export class UpdateUserDto {
	@IsNotEmpty()
	@MaxLength(255)
	@IsString()
	login: string;

	@IsDefined()
	@IsArray()
	@IsInt({ each: true })
	@Transform((roleIds: string[]) => roleIds.map(id => Number(id)))
	roleIds: number[];
}