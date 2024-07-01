import { QueryRunner } from 'typeorm';

import { ITransactionOptions } from './transaction-options';

export type ServiceMethod<T> = (
    queryRunner: QueryRunner,
    options: ITransactionOptions,
) => Promise<T>;

export interface IThrowErrorMethodOptions extends ITransactionOptions {
	errorMessage?: string;
}