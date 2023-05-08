
import { Injectable } from '@angular/core';
import { IEnv } from '../model/_interfaces';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EnvironmentService implements IEnv {

  get production() {
    return environment.production;
  }

  get enableDebugTools() {
    return environment.enableDebugTools;
  }

  get logLevel() {
    return environment.logLevel;
  }

  get apiHost() {
    return environment.apiHost;
  }

  get apiUrl() {
    return environment.apiUrl;
  }

  constructor() {}
  
}