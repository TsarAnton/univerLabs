import { QueryRunner } from 'typeorm';

import { ITransactionOptions } from 'src/types/transaction-options';

export type ServiceMethod<T> = (
    QueryRunner: QueryRunner,
    options: ITransactionOptions,
) => Promise<T>;

export type ReadAllResult<T> = {
    totalRecordsNumber: number;
    entities: T[];
}

export interface IThrowErrorMethodOptions extends ITransactionOptions {
	errorMessage?: string;
}