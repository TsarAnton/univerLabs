import {
	Column,
	DeleteDateColumn,
	Entity,
	OneToMany,
	PrimaryGeneratedColumn,
} from 'typeorm';

import { UserToRole } from './user-to-role.entity';
import { Repair } from './repair.entity';
import { RouteList } from './route-list.entity';

@Entity({ name: 'users', engine: 'InnoDB' })
export class User {
	@PrimaryGeneratedColumn()
	id: number;

	@Column({ length: 100, nullable: true, default: null })
	login: string;

	@Column({ length: 100 })
	password: string;

	@OneToMany(
		() => UserToRole,
		userToRole => userToRole.user,
	)
	userToRoles: UserToRole[];

	@OneToMany(
		() => Repair,
		repair => repair.user,
	)
	repairs: Repair[];

	@OneToMany(
		() => RouteList,
		driverRouteList => driverRouteList.driver,
	)
	driverRouteLists: RouteList[];

	@OneToMany(
		() => RouteList,
		dispatcherRouteList => dispatcherRouteList.dispatcher,
	)
	dispatcherRouteLists: RouteList[];

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}
