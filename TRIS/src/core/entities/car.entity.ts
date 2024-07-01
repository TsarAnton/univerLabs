import {
	Column,
	DeleteDateColumn,
	Entity,
	OneToMany,
	PrimaryGeneratedColumn,
} from 'typeorm';

import { Repair } from './repair.entity';
import { RouteList } from './route-list.entity';

@Entity({ name: 'cars', engine: 'InnoDB' })
export class Car {
	@PrimaryGeneratedColumn()
	id: number;

	@Column({ length: 100 })
	mark: string;

	@Column({ length: 100, nullable: false, default: null })
	model: string;

	@OneToMany(
		() => Repair,
		repair => repair.car,
	)
	repairs: Repair[];

	@OneToMany(
		() => RouteList,
		routeList => routeList.car,
	)
	routeLists: RouteList[];

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}