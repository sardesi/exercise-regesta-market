import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { LocalStorageService } from '../services/local-storage.service';
import { AuthService } from '../services/auth.service';
import { MessageService } from '../services/message-service';
import { UserService } from '../services/user.service';
import { AuthGuard } from './auth.guard';

@Injectable({
  providedIn: 'root'
})
export class FirstAccessGuard extends AuthGuard implements CanActivate {

  constructor(
    protected authService: AuthService,
    protected localStorageService: LocalStorageService,
    protected messageService: MessageService,
    protected router: Router,
    private userService: UserService
  ) {
    super(authService, localStorageService, messageService, router);
  }  

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    
    return super.canActivate(route, state).pipe(
      map( 
        (result: boolean) => {
          
          if(!result) return false;

          if(this.userService.isLogged()) {
            this.router.navigate(['/product']);
          }

          return true;

        }
      )
    );

  }
  
}
