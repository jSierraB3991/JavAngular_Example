import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Serie } from "../models/Serie";
import { TokenServiceService } from "./token-service.service";

@Injectable({
    providedIn: 'root'
})
export class SerieAdminService{
    
    constructor(private http: HttpClient,
        private tokenService: TokenServiceService) { 
    }

    public get(): Observable<Array<Serie>>{
        return this.http.get<Array<Serie>>(environment.urlApi+"/serie",{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public post(serie: Serie): Observable<Serie>{
        return this.http.post<Serie>(environment.urlApi+"/serie", serie,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public put(serie: Serie): Observable<Serie>{
        return this.http.put<Serie>(environment.urlApi+"/serie/"+serie.id, serie,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public delete(idSerie: number): Observable<Object>{
        return this.http.delete(environment.urlApi+"/serie/"+idSerie , {
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }
}