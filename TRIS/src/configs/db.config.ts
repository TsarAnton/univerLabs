import { TypeOrmModuleOptions } from '@nestjs/typeorm';
const dbconfig: TypeOrmModuleOptions = {
  type: 'postgres',
  host: 'localhost',
  port: 5432,
  username: 'postgres',
  password: '2003',
  database: 'buff',
  entities: [],
  autoLoadEntities: true,
  migrations: []
};
export { dbconfig };
