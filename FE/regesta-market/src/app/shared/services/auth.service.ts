import { Injectable } from '@angular/core';
import { Observable, of, } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { ApiService } from './api.service';
import { map, switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastService } from './toast-service';
import { MarketUser } from '../model/_interfaces';
import { UserService } from './user.service';
//import { NotificheService } from '../../notifiche/services/notifiche.service';

const URL_CONTEXT = "/api/user/context";
const URL_LOGIN = "/login/doLogin";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private apiService: ApiService,
    private localStorageService: LocalStorageService,
    private router: Router,
    private userService: UserService,
  ) { }

  public login(mail: string,  password: string, rememberMe: boolean = false): Observable<any> {

    const utente: MarketUser = { mail, password };

    utente.mail = mail;
    utente.password = password;

    return this.apiService.post(URL_LOGIN, utente, { rememberMe });
    
  }

  public logout(doApiLogout: boolean = true) {

    this.setCurrentUser(null);
    this.localStorageService.remove(LocalStorageService.STORAGE_JWT_TOKEN);
    this.router.navigate(['/login']);

  }

  public context(): Observable<MarketUser> {

    let user = { 
      id: 1,
      mail: "simoneardesi@outlook.it", 
      password: null,
      name: "Simone",
      surname: "Ardesi",
      language: "IT"
    };

    this.setCurrentUser(user);

    return of(user);

    // TODO: Riabilita una volta attivata security

    // return this.apiService.authGet(URL_CONTEXT, null).pipe(
    //   map( (user: MarketUser) => {
    //     this.setCurrentUser(user);
    //     return user;
    //   })
    // );

  }

  public initUser(mail: string,  password: string, rememberMe: boolean = false): Observable<MarketUser> {
    return this.login(mail, password, rememberMe).pipe(switchMap(resLogin => this.manageAuth(resLogin.token)));
  }


  private setCurrentUser(user : MarketUser | null) {
    this.userService.setCurrentUser(user);
  }

  public getCurrentUser(): MarketUser | null {
    return this.userService.getCurrentUser();
  }

  public manageAuth(token: string): Observable<MarketUser> {

    this.localStorageService.remove(LocalStorageService.STORAGE_JWT_TOKEN);
    this.localStorageService.set(LocalStorageService.STORAGE_JWT_TOKEN, token);

    this.localStorageService.set(LocalStorageService.STORAGE_HAS_BEEN_HERE, "true");

    const userAuth = new Observable<MarketUser>((observer) => {

      this.context().subscribe(response =>  {

        this.setCurrentUser(response);
        this.localStorageService.set(LocalStorageService.STORAGE_LANGUAGE_KEY, response.language!);

        //this.notificheService.startNotificationsCount();

        observer.next(this.getCurrentUser()!);
        observer.complete();

      });

    });

    return userAuth;
    
  }

}
