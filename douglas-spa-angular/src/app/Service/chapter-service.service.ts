import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Chapter } from "../models/Chapter";
import { TokenServiceService } from "./token-service.service";

@Injectable({
    providedIn: 'root'
})
export class ChapterService{
  
    constructor(private http: HttpClient,
        private tokenService: TokenServiceService) { 
    }

    public getBySeason(idSeason: number): Observable<Array<Chapter>>{
        return this.http.get<Array<Chapter>>(environment.urlApi+"/season/chapters/season/"+ idSeason,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }
    
    public save(chpater: string,idSeason: number): Observable<Object>{
        return this.http.post<Object>(environment.urlApi+"/season/add-url-video/"+ idSeason, chpater,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }

    public delete(idSeason: number, idChapter: number): Observable<Object>{
        return this.http.delete<Object>(environment.urlApi+"/season/delete-chapter/season/"+idSeason+"/chapter/"+ idChapter,{
            headers: new HttpHeaders({
                "Authorization": this.tokenService.getTokenType() +" " + this.tokenService.getToken()
            })
        });
    }
}