import { IEnv } from "src/app/shared/model/_interfaces";

const apiHost = 'my-special-site.com';
const apiUrl = 'http://localhost:4200/regesta-market';

export const environment: IEnv = {
  production: true,
  enableDebugTools: true,
  logLevel: 'debug',
  apiHost,
  apiUrl,
};

