import { IsNotEmpty, IsString, MaxLength } from 'class-validator';

export class CreateRouteDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    begPoint: string;

    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    endPoint: string;
}

export class UpdateRouteDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    begPoint: string;

    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    endPoint: string;
}