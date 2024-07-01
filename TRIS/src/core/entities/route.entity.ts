import {
	Column,
	DeleteDateColumn,
	Entity,
	OneToMany,
	PrimaryGeneratedColumn,
} from 'typeorm';

import { RouteList } from './route-list.entity';

@Entity({ name: 'routes', engine: 'InnoDB' })
export class Route {
	@PrimaryGeneratedColumn()
	id: number;

	@Column({ length: 100 })
	begPoint: string;

	@Column({ length: 100 })
	endPoint: string;

	@OneToMany(
		() => RouteList,
		routeList => routeList.route,
	)
	routeLists: RouteList[];

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}