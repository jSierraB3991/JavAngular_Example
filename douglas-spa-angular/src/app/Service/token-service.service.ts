import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TokenServiceService {

  constructor() { }

  public setToken(token: string): void{
    window.sessionStorage.removeItem(environment.tokenKey);
    window.sessionStorage.setItem(environment.tokenKey, token);
  }

  public getToken(): string|null {
    return window.sessionStorage.getItem(environment.tokenKey);
  }
  
  public setUserName(userName: string): void{
    window.sessionStorage.removeItem(environment.userNameKey);
    window.sessionStorage.setItem(environment.userNameKey, userName);
  }

  public getUserName(): string|null {
    return window.sessionStorage.getItem(environment.userNameKey);
  }

  public logOut(): void{
    window.sessionStorage.removeItem(environment.tokenKey);
    window.sessionStorage.removeItem(environment.userNameKey);
  }
}
