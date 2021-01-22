import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Category } from "../models/Category";
import { TokenServiceService } from "./token-service.service";

@Injectable({
    providedIn: 'root'
})
export class CategoryService{
    
    constructor(private http: HttpClient,
        private tokenService: TokenServiceService) { 
    }

    public getCategories(): Observable<Array<Category>>{
        return this.http.get<Array<Category>>(environment.urlApi+"/category",{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public postCategories(category: Category): Observable<Category>{
        return this.http.post<Category>(environment.urlApi+"/category", category,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public putCategories(category: Category): Observable<Category>{
        return this.http.put<Category>(environment.urlApi+"/category", category,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public deleteCategories(category: Category): Observable<Object>{
        return this.http.delete(environment.urlApi+"/category/"+category.id , {
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }
}