import { 
	DeleteDateColumn, 
	Entity, 
	JoinColumn, 
	ManyToOne, 
	PrimaryGeneratedColumn 
} from 'typeorm';

import { Role } from './role.entity';
import { User } from './user.entity';

@Entity({ name: 'users_roles', engine: 'InnoDB' })
export class UserToRole {
	@PrimaryGeneratedColumn()
	id: number;

	@ManyToOne(
		() => Role,
		role => role.userToRoles,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'role_id', referencedColumnName: 'id' })
	role: Role;

	@ManyToOne(
		() => User,
		user => user.userToRoles,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'user_id', referencedColumnName: 'id' })
	user: User;

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}
