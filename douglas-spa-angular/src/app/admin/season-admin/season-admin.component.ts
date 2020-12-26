import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Season } from 'src/app/models/Season';
import { SeasonService } from 'src/app/Service/season-service.service';


declare var M: any;

@Component({
  selector: 'app-season-admin',
  templateUrl: './season-admin.component.html',
  styleUrls: ['./season-admin.component.css']
})
export class SeasonAdminComponent implements OnInit {

  idSerie: number = 0;
  name: string = "";
  remarks: string = "";
  id: number = 0;
  seasons: Array<Season> = [];

  constructor(private seasonService: SeasonService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.queryParams.forEach(param => {
        if(param.idSerie){
          this.idSerie = param.idSerie;
        }
        else{
          this.router.navigate(['/admin/serie']);
        }
    });

    this.get();
  }

  get(): void{
    this.seasonService.get(this.idSerie).subscribe(
      data => {
        this.seasons = data;
      },
      error =>{
        console.log(error);
        M.toast({html: "Error al tratar de traer los datos"});
      }
    );
  }

  resetForm(): void{
    this.id = 0;
    this.name = "";
    this.remarks = "";
  }

  question(season: Season): void{
    let isDelete: Boolean = window.confirm("Seguro de querer eliminar la temporada " + season.name);
    
    if(isDelete){ 
      this.seasonService.delete(season.id).subscribe(
        data =>{
          M.toast({html: 'Temporada ' + season.name + ' Eliminada'});
          this.resetForm();
          this.get();
        },
        error => {M.toast({html: error.error.message});
        this.resetForm();
      });
    }
    else{
      this.resetForm();
    }
  }
  save(): void{

    if(this.id == 0){
      this.seasonService.post({
        id: 0,
        name: this.name,
        remarks: this.remarks,
        videosUrl: [],
      }, this.idSerie).subscribe(
        data=>{
          this.resetForm();
          this.get();
        },
        error => M.toast({html: error.error.message})
      );
    }

  }

}
