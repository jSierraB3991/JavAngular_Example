import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { ResultPage } from '../models/ResultPage';
import { Category } from '../models/Category';
import { LoginRequest } from '../models/LoginRequest';
import { Jwt } from '../models/jwt';
import { User } from '../models/User';
import { TokenServiceService } from './token-service.service';

@Injectable({
  providedIn: 'root'
})
export class SerieServiceService {

  constructor(private http: HttpClient,
            private tokenService: TokenServiceService) { 
  }

  public getSeries(): Observable<ResultPage>{
    console.log("pidiendo consulta a: " + environment.urlApi+"/public/serie");
    return this.http.get<ResultPage>(environment.urlApi+"/public/serie", {
      headers: new HttpHeaders({
        'user-name':  this.tokenService.getUserName()
      })
     });
  }

  public getCategories(): Observable<Array<Category>>{
    console.log("pidiendo consulta a: " + environment.urlApi+"/public/category");
    return this.http.get<Array<Category>>(environment.urlApi+"/public/category");
  }

  public login(login: LoginRequest): Observable<Jwt>{
    console.log("pidiendo consulta a: " + environment.urlApi+"/public/authorization/login");
    return this.http.post<Jwt>(environment.urlApi + "/public/authorization/login", login);
  }

  public register(user: User): Observable<User>{
    console.log("pidiendo consulta a: " + environment.urlApi+"/public/authorization/save-user");
    return this.http.post<User>(environment.urlApi + "/public/authorization/save-user", user);
  }
}
