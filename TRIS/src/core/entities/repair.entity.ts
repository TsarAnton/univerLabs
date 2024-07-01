import {
	Column,
	DeleteDateColumn,
	Entity,
	JoinColumn,
	ManyToOne,
	OneToMany,
	PrimaryGeneratedColumn,
} from 'typeorm';

import { Car } from './car.entity';
import { User } from './user.entity';

@Entity({ name: 'repairs', engine: 'InnoDB' })
export class Repair {
	@PrimaryGeneratedColumn()
	id: number;

	@Column({ length: 200 })
	discription: string;

	@Column({ name: 'begTime', type: 'timestamp' })
	begTime: Date;

	@Column({ name: 'endTime', type: 'timestamp' })
	endTime: Date;

	@ManyToOne(
		() => Car,
		car => car.repairs,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'car_id', referencedColumnName: 'id' })
	car: Car;

	@ManyToOne(
		() => User,
		user => user.repairs,
		{ onDelete: 'RESTRICT', onUpdate: 'RESTRICT' },
	)
	@JoinColumn({ name: 'user_id', referencedColumnName: 'id' })
	user: User;

	@DeleteDateColumn({ name: 'deleted_at', type: 'timestamp' })
	deletedAt: Date;
}