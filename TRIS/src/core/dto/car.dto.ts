import { IsNotEmpty, IsString, MaxLength } from 'class-validator';

export class CreateCarDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    mark: string;

    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    model: string
}

export class UpdateCarDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    mark: string;

    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    model: string
}