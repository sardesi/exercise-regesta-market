import {Injectable} from '@angular/core';
import {
  HttpErrorResponse,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';

import { forkJoin, Observable, of, throwError } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { EnvironmentService } from '../services/environment.service';
import { LocalStorageService } from '../services/local-storage.service';
import { AuthService } from '../services/auth.service';
import { MessageService } from '../services/message-service';


// TODO: Possibilmente spostare nell'env quando creato.
const API_LOGIN_LINK: string = "/login/doLogin";
const DOCUMENT_DOWNLOAD_LINK: string = "/api/documento/downloadDocumento/";

const API_KEY_HEADER: string = "api_key";
const RESPONSE_LANG_CODE_HEADER: string = "smartworker_responselangcode";

const ERROR_STATUS_NO_CONNECTION = 0;
const ERROR_STATUS_UNAUTHORIZED = 401;
const ERROR_STATUS_CUSTOM_LANG_EXCEPTION = 422;

@Injectable()
export class CustomHttpInterceptor implements HttpInterceptor {

  constructor(
    private environmentService: EnvironmentService,
    private localStorageService: LocalStorageService,
    private authService:  AuthService,
    private messageService: MessageService,
    private router: Router,

  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<any>   {

    const enrichedRequest = request.clone({
      headers: request.headers,
      withCredentials: true
    });


    return next.handle(enrichedRequest).pipe(

      switchMap((event) => {
        if (event instanceof HttpResponse) {
          // Per ora nulla, lasciato in caso serva.
        }
        return forkJoin({event: of(event)});
      }),

      map((response: any) => {
        const { event } = response;
        return event;
      }),

      catchError((error: HttpErrorResponse) => {
        let errorMessage = '';

      // Server non raggiungibile.
        if (error.status === ERROR_STATUS_NO_CONNECTION) {

          //TODO: this.toastr.error("Il server non è momentanemaente raggiungibile. Riprovare più tardi.");
          return throwError(error);

        }

      // Unauthorized
        if (error.status === ERROR_STATUS_UNAUTHORIZED) {

          this.localStorageService.remove(LocalStorageService.STORAGE_JWT_TOKEN);

          // Gestione diversa se login o se è un 401 generico.
          if(enrichedRequest.url.startsWith(this.environmentService.apiUrl + API_LOGIN_LINK)) {

            if(error?.error?.reason) {
              //TODO: this.messageService.error("EXCEPTIONS." + error?.error?.reason[0])
            } else {
              //TODO: this.messageService.error("EXCEPTIONS.WRONG_CREDENTIALS");
            }

          } else {

            if(error?.headers?.get(RESPONSE_LANG_CODE_HEADER)) {
              //TODO: this.messageService.error("EXCEPTIONS." + error.headers.get(RESPONSE_LANG_CODE_HEADER));
            } else {
              //TODO: this.messageService.error("EXCEPTIONS.UNAUTHORIZED");
            }

          }

          this.authService.logout(false);
          return throwError(error);

        }

      // Eccezioni custom
        if(error.status === ERROR_STATUS_CUSTOM_LANG_EXCEPTION) {
          const errorCode = enrichedRequest.url.startsWith(this.environmentService.apiUrl + DOCUMENT_DOWNLOAD_LINK) ? error.error : error.error.code;
          //TODO: this.messageService.error("EXCEPTIONS." + errorCode);
          return throwError(error);

        }

      // Errori non gestiti
        if (error.error && error.error.errorMessage) {
          errorMessage = error.error.errorMessage;
        } else if (error.error instanceof ErrorEvent) {
          errorMessage = `Error: ${error.error.message}`;
        } else {
          errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }

        //TODO: this.messageService.error(`${errorMessage}`, undefined, false);

        return throwError(error);

      }));

  }
}
