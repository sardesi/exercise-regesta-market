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