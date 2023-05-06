import { LogLevel } from "./_types";

export interface IAssociativeArray {
    [key: string]: string | boolean | number;
}

export interface IEnv {
    production: boolean;
    enableDebugTools: boolean;
    logLevel: LogLevel;
    apiUrl: string;
    apiHost: string;
}

export interface MarketUser {
    id?: number;
	mail: string;
	password: string;
	name?: string;
	surname?: string;
	language?: string;
}

export interface Pagination {
    desc?: boolean;
	limit: number;
	order?: string;
	offset: number;
	totalRecords?: number;
}

export interface ProductsListRequest {
    code?: string;
    name?: string;
    pagination: Pagination;
}

export interface ListResponse<T> {
    pagination: Pagination;
	results: T[];
}

export interface TranslatedProduct {
    code: string;
	description?: string;
	id: number;
	name: string;
}