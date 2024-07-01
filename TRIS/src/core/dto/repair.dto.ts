import { Transform } from 'class-transformer';
import { IsDate, IsDefined, IsInt, IsNotEmpty, IsString, MaxLength } from 'class-validator';

export class CreateRepairDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    disciption: string;

    @IsNotEmpty()
    @IsDate()
    begTime: Date;

    @IsDate()
    endTime: Date;

    @IsDefined()
    @IsInt()
    @Transform(carId => Number(carId))
    carId: number;

    @IsDefined()
    @IsInt()
    @Transform(userId => Number(userId))
    userId: number;
}

export class UpdateRepairDto {
    @IsNotEmpty()
    @MaxLength(50)
    @IsString()
    discription: string;

    @IsNotEmpty()
    @IsDate()
    begTime: Date;

    @IsDate()
    endTime: Date;

    @IsDefined()
    @IsInt()
    @Transform(carId => Number(carId))
    carId: number;

    @IsDefined()
    @IsInt()
    @Transform(userId => Number(userId))
    userId: number;
}