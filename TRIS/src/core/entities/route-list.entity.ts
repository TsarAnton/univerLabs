import {
	Column,
	DeleteDateColumn,
	Entity,
	JoinColumn,
	ManyToOne,
	PrimaryGeneratedColumn,
} from 'typeorm';

import { Car } from './car.entity';
import { User } from './user.entity';
import { Route } from './route.entity';

@Entity({ name: 'routes_lists', engine: 'InnoDB' })
export class RouteList {
	@PrimaryGeneratedColumn()
	id: number;

	@Column()
	isEnded: boolean;

	@ManyToOne(
		() => Car,
		car => car.routeLists,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'car_id', referencedColumnName: 'id' })
	car: Car;

	@ManyToOne(
		() => User,
		user => user.driverRouteLists,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'driver_id', referencedColumnName: 'id' })
	driver: User;

    @ManyToOne(
		() => User,
		user => user.dispatcherRouteLists,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'dispatcher_id', referencedColumnName: 'id' })
	dispatcher: User;

    @ManyToOne(
		() => Route,
		route => route.routeLists,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'route_id', referencedColumnName: 'id' })
	route: Route;

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}