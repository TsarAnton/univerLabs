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
import { CarsService } from '../services/cars.service';
import { CreateCarDto } from '../dto/car.dto';
import { UpdateCarDto } from '../dto/car.dto';
import { FrontenedCar } from '../types/cars.types';

@Controller('students')
export class CarsController extends BaseController {
    constructor(
        protected connection: Connection,
        private carService: CarsService,
    ) {
        super(connection);
    }

    @Post()
    @HttpCode(HttpStatus.CREATED)
    public async create(@Body() createCarDto: CreateCarDto): Promise<FrontenedCar> {
        return this.execInTransaction<FrontenedCar>(async queryRunner => {
            const car = this.carService.createCarEntityFrom({
                mark: createCarDto.mark,
                model: createCarDto.model,
            })
            const createdCar = await this.carService.create(car, { queryRunner });

            return new FrontenedCar(createdCar);
        });
    }

    @Get()
    public async readAll(): Promise<ReadAllResult<FrontenedCar>> {
        const cars  = await this.carService.readAll();

        return {
            totalRecordsNumber: cars.totalRecordsNumber,
            entities: cars.entities.map(car => new FrontenedCar(car)),
        };
    }

    @Get(':id')
    public async readById(@Param('id') id: number): Promise<FrontenedCar> {
        const car = await this.carService.readById(id);

        return new FrontenedCar(car);
    }

    @Patch(':id')
    public async update(
        @Param('id') id: number,
        @Body() updateCarDto: UpdateCarDto,
    ): Promise<FrontenedCar> {
        const car = this.carService.createCarEntityFrom({
            mark: updateCarDto.mark,
            model: updateCarDto.model,
        });
        const updatedCar = await this.carService.update(id, car);

        return new FrontenedCar(updatedCar);
    }

    @Delete(':id')
    @HttpCode(HttpStatus.NO_CONTENT)
    public async delete(@Param('id') id: number): Promise<void> {
        await this.carService.delete(id);
    }
}