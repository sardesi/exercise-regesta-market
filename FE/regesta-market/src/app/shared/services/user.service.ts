import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { MarketUser } from '../model/_interfaces';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private currentUser: MarketUser | null = null;

  private _userChangeEvent = new Subject<MarketUser | null>();
  public get userChangeEvent() {
    return this._userChangeEvent;
  }

  constructor() { }

  public getCurrentUser(): MarketUser | null {
    return this.currentUser;
  }

  public setCurrentUser(user : MarketUser | null) {
    this.currentUser = user;
    this.userChangeEvent.next(this.currentUser);
  }

  public isLogged() {
    return this.currentUser != null;
  }

  public getUserName() {
    return this.currentUser?.name;
  }

  public getFullName() {
    return this.currentUser?.name + this.currentUser?.surname;
  }

  public getUserInitials() {
    return this.currentUser?.name?.charAt(0)! + this.currentUser?.surname?.charAt(0)!;
  }

  public getMail() {
    return this.currentUser?.mail;
  }

}
