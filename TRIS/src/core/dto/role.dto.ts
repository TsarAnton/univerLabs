import { IsNotEmpty, IsString, MaxLength } from 'class-validator';

export class CreateRoleDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    name: string;
}

export class UpdateRoleDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    name: string;
}