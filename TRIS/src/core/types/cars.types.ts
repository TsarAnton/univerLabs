import { Car } from "../entities/car.entity";

export class FrontenedCar {
    public id: number;
    public mark: string;
    public model: string;
    
    constructor(car: Car) {
        this.id = car.id;
        this.mark = car.mark;
        this.model = car.model;
    }
}