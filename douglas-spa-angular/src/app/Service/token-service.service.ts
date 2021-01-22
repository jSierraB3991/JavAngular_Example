import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TokenServiceService {

  constructor() { }

  
  public setRole(role: string): void{
    window.sessionStorage.removeItem(environment.roleUSer);
    window.sessionStorage.setItem(environment.roleUSer, role);
  }

  public getRole(): string|null {
    return window.sessionStorage.getItem(environment.roleUSer);
  }

  public setToken(token: string): void{
    window.sessionStorage.removeItem(environment.tokenKey);
    window.sessionStorage.setItem(environment.tokenKey, token);
  }

  public getToken(): string|null {
    return window.sessionStorage.getItem(environment.tokenKey);
  }

  public setTokenType(type: string): void{
    window.sessionStorage.removeItem(environment.tokenType);
    window.sessionStorage.setItem(environment.tokenType, type);
  }

  public getTokenType(): string|null {
    return window.sessionStorage.getItem(environment.tokenType);
  }
  
  public setUserName(userName: string): void{
    window.sessionStorage.removeItem(environment.userNameKey);
    window.sessionStorage.setItem(environment.userNameKey, userName);
  }

  userName: string|null = "";
  public getUserName(): string {
    this.userName = window.sessionStorage.getItem(environment.userNameKey);
    if(this.userName==null) return "";
    return this.userName.trim();
  }

  public logOut(): void{
    window.sessionStorage.removeItem(environment.tokenKey);
    window.sessionStorage.removeItem(environment.userNameKey);
    window.sessionStorage.removeItem(environment.roleUSer);
    window.sessionStorage.removeItem(environment.tokenType);
  }
}
