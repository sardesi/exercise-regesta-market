import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { forkJoin, Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    protected authService: AuthService,
    protected localStorageService: LocalStorageService,
    protected messageService: MessageService,
    protected router: Router,
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {

    if(!this.localStorageService.get(LocalStorageService.STORAGE_JWT_TOKEN)) {
      if(route.data.isLogin) {
        return of(true);
      }
      this.messageService.error("EXCEPTIONS.UNAUTHORIZED");
      this.router.navigate(['/login']);
      return of(false);
    }

  // Oltre al context qua posso eseguire operazioni necessarie su tutte le pagine, in modo da essere sempre allineato. Anche a doppio step, in quanto alcune cose richiedono il profilo.
  // Ove possibile restituisco sempre degli of() in modo da evitare chiamate ripetute se non necessarie.
    return forkJoin(
      [
        this.authService.context()
      ]
    ).pipe(
      map( results => {

        return true;

      })
    );

  }
  
}
