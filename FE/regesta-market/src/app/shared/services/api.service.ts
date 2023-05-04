import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EnvironmentService } from './environment.service';
import { LocalStorageService } from './local-storage.service';
import { IAssociativeArray } from '../model/_interfaces';

const NO_LOADER_HEADER: string = "regesta_market_no_loader";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(
    private environmentService: EnvironmentService,
    private localStorageService: LocalStorageService,
    private httpClient: HttpClient
  ) { }


  /* GET */

// TODO: una volta attivata anche a BE mettere useRoleApiUrl con default a true, poi fare un search all ed eliminare tutti gli "/api/" dalle chiamate.
  authGet(pathUrl: string, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpGet(pathUrl, params, true, contentType, responseType, useRoleApiUrl, noLoader);
  }

  get(pathUrl: string, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpGet(pathUrl, params, false, contentType, responseType, useRoleApiUrl, noLoader);
  }

  private httpGet(pathUrl: string, params: IAssociativeArray | null, auth: boolean, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    let apiUrl = this.environmentService.apiUrl;
    const queryString = this.buildQueryString(params);
    const httpOptions = this.buildOptions(auth, contentType, noLoader);
    if (responseType) { httpOptions.responseType = responseType; }
    return this.httpClient.get(`${apiUrl}${pathUrl}${queryString}`, httpOptions);
  }

  /* POST */

  authPost(pathUrl: string, body: any, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpPost(pathUrl, body, params, true, contentType, responseType, useRoleApiUrl, noLoader);
  }

  authPut(pathUrl: string, body: any, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpPut(pathUrl, body, params, true, contentType, responseType, useRoleApiUrl, noLoader);
  }

  post(pathUrl: string, body: any, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpPost(pathUrl, body, params, false, contentType, responseType, useRoleApiUrl, noLoader);
  }

  private httpPost(pathUrl: string, body: any, params: IAssociativeArray | null, auth: boolean, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    let apiUrl = this.environmentService.apiUrl;
    const queryString = this.buildQueryString(params);
    const httpOptions = this.buildOptions(auth, contentType, noLoader);
    if (responseType) { httpOptions.responseType = responseType; }
    return this.httpClient.post(`${apiUrl}${pathUrl}${queryString}`, body, httpOptions);

  }

  private httpPut(pathUrl: string, body: any, params: IAssociativeArray | null, auth: boolean, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    let apiUrl = this.environmentService.apiUrl;
    const queryString = this.buildQueryString(params);
    const httpOptions = this.buildOptions(auth, contentType, noLoader);
    if (responseType) { httpOptions.responseType = responseType; }
    return this.httpClient.put(`${apiUrl}${pathUrl}${queryString}`, body, httpOptions);

  }

  /* DELETE */

  authDelete(pathUrl: string, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpDelete(pathUrl, params, true, contentType, responseType, useRoleApiUrl, noLoader);
  }

  delete(pathUrl: string, params: IAssociativeArray | null, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    return this.httpDelete(pathUrl, params, false, contentType, responseType, useRoleApiUrl, noLoader);
  }

  private httpDelete(pathUrl: string, params: IAssociativeArray | null, auth: boolean, contentType?: string | null, responseType?: string | null, useRoleApiUrl: boolean = false, noLoader: boolean = false): Observable<any> {
    let apiUrl = this.environmentService.apiUrl;
    const queryString = this.buildQueryString(params);
    const httpOptions = this.buildOptions(auth, contentType, noLoader);
    if (responseType) { httpOptions.responseType = responseType; }
    return this.httpClient.delete(`${apiUrl}${pathUrl}${queryString}`, httpOptions);
  }


  /* Funzioni utility */

  private buildQueryString(params: IAssociativeArray | null) {
    let queryString = '';
    if (params != null) {
      queryString = '?' + Object.keys(params).map(function(key){return key + '=' + params[key]; }).join('&');
    }
    return queryString;
  }

  private buildOptions(auth: boolean, contentType: string | null = 'application/json', noLoader: boolean): any {

    const options: any = {};

    if (auth) {
      const token: string | null = this.localStorageService.get(LocalStorageService.STORAGE_JWT_TOKEN);
      options.api_key = token ? token : '';
    }

    if (contentType) { options['Content-Type'] = contentType; }

    if(noLoader) options[NO_LOADER_HEADER] = "true";

    return { headers: new HttpHeaders( options ) };

  }

}
