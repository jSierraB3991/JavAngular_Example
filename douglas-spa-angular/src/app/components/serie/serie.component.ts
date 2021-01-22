import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chapter } from 'src/app/models/Chapter';
import { SerieServiceService } from 'src/app/Service/serie-service.service';

declare var M: any;

@Component({
  selector: 'app-serie',
  templateUrl: './serie.component.html',
  styleUrls: ['./serie.component.css']
})
export class SerieComponent implements OnInit {

  idSerie: number = 0;  
  chapters: Array<Chapter> = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private serieService: SerieServiceService) { }

  ngOnInit(): void {
    this.route.queryParams.forEach(param => {
      if(param.idSerie){
        this.idSerie = param.idSerie;
      }
      else{
        this.router.navigate(['/serie']);
      }
    });

    this.getSerie();
  }

  getSerie(): void{
    this.serieService.getById(this.idSerie).subscribe(
      data => {
        console.log(data.seasons.forEach((season) =>{
          if(season.videosUrl.length > 0){
            season.videosUrl.forEach((vd) =>{
              this.chapters.push({
                id: 0,
                urlVideo: vd
              })
            });
          }
        }));   
      },
      error => {
        console.log(error);
        M.toast({html: "No se pudo traer la data"});
      }
    );
  }

}
