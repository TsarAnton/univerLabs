import { 
	Column, 
	DeleteDateColumn, 
	Entity, 
	OneToMany, 
	PrimaryGeneratedColumn 
} from 'typeorm';

import { UserToRole } from './user-to-role.entity';

@Entity({ name: 'roles', engine: 'InnoDB' })
export class Role {
	@PrimaryGeneratedColumn()
	id: number;

	@Column({ length: 50 })
	name: string;

	@OneToMany(
		() => UserToRole,
		userToRole => userToRole.role,
	)
	userToRoles: UserToRole[];

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}
