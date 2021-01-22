import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Season } from "../models/Season";
import { TokenServiceService } from "./token-service.service";

@Injectable({
    providedIn: 'root'
})
export class SeasonService {

    constructor(private http: HttpClient,
        private tokenService: TokenServiceService) { 
    }

    public get(idSerie: number): Observable<Array<Season>>{
        return this.http.get<Array<Season>>(environment.urlApi+"/season/by-serie/" + idSerie,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }
    
    public getById(idSeason: number): Observable<Season>{
        return this.http.get<Season>(environment.urlApi+"/season/" + idSeason,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public post(season: Season, idSerie: number): Observable<Season>{
        return this.http.post<Season>(environment.urlApi+"/serie/add-season/" + idSerie, season,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public put(season: Season): Observable<Season>{
        return this.http.put<Season>(environment.urlApi+"/season", season,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public delete(id:number): Observable<Object>{
        return this.http.delete(environment.urlApi+"/season/"+id , {
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

}