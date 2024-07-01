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
	HttpStatus,
	Query,
} from '@nestjs/common';

import { Connection } from 'typeorm';

import { BaseController } from 'src/common/base-controller';
import { ReadAllResult } from 'src/common/base-service.types';
import { CreateRepairDto } from '../dto/repair.dto';
import { UpdateRepairDto } from '../dto/repair.dto';
import { RepairsService } from '../services/repairs.service';
import { UsersService } from '../services/users.service';
import { CarsService } from '../services/cars.service';
import { FrontenedRepair } from '../types/repairs.types';

@Controller('repairs')
export class RepairsController extends BaseController {
    constructor(
        protected connection: Connection,
        private repairsService: RepairsService,
        private usersService: UsersService,
        private carsService: CarsService,
    ) {
        super(connection);
    }

    @Post()
    @HttpCode(HttpStatus.CREATED)
    public async create(@Body() createRepairDto: CreateRepairDto): Promise<FrontenedRepair> {
        return this.execInTransaction<FrontenedRepair>(async queryRunner => {
            const repair = this.repairsService.createRepairEntityFrom({
                discription: createRepairDto.disciption,
                begTime: createRepairDto.begTime,
                endTime: createRepairDto.endTime,
                user: {
                    id: createRepairDto.userId,
                },
                car: {
                    id: createRepairDto.carId,
                },
            })
            const createdRepair = await this.repairsService.create(repair, { queryRunner });

            createdRepair.car = await this.carsService.readById(
                createdRepair.car.id,
                { queryRunner },
            );

            createdRepair.user = await this.usersService.readById(
                createdRepair.user.id,
                { queryRunner },
            );

            return new FrontenedRepair(createdRepair);
        });
    }

    @Get()
    public async readAll(): Promise<ReadAllResult<FrontenedRepair>> {
        const repairs = await this.repairsService.readAll();

        return {
            totalRecordsNumber: repairs.totalRecordsNumber,
            entities: repairs.entities.map(repair => new FrontenedRepair(repair)),
        };
    }

    @Get(':id')
    public async readById(@Param('id') id: number): Promise<FrontenedRepair> {
        const repair = await this.repairsService.readById(id);

        return new FrontenedRepair(repair);
    }

    @Patch(':id')
    public async update(
        @Param('id') id: number,
        @Body() updateRepairDto: UpdateRepairDto,
    ): Promise<FrontenedRepair> {
        const repair = this.repairsService.createRepairEntityFrom({
            discription: updateRepairDto.discription,
            begTime: updateRepairDto.begTime,
            endTime: updateRepairDto.endTime,
            user: {
                id: updateRepairDto.userId,
            },
            car: {
                id: updateRepairDto.carId,
            },
        });
        const updatedRepair = await this.repairsService.update(id, repair);

        return new FrontenedRepair(updatedRepair); 
    }

    @Delete(':id')
    @HttpCode(HttpStatus.NO_CONTENT)
    public async delete(@Param('id') id:number): Promise<void> {
        await this.repairsService.delete(id);
    }
}