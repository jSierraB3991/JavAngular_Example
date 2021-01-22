import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Chapter } from 'src/app/models/Chapter';
import { ChapterService } from 'src/app/Service/chapter-service.service';
import { SeasonService } from 'src/app/Service/season-service.service';

declare var M: any;

@Component({
  selector: 'app-capther-season',
  templateUrl: './capther-season.component.html',
  styleUrls: ['./capther-season.component.css']
})
export class CaptherSeasonComponent implements OnInit {

  chapters: Array<Chapter> = [];
  idSeason: number = 0;
  urlVideo: string = "";

  constructor(private route: ActivatedRoute,
              private router: Router,
              private seasonService: SeasonService,
              private chapterService: ChapterService) {
  }

  ngOnInit(): void {
    this.route.queryParams.forEach(param => {
      if(param.idSeason){
        this.idSeason = param.idSeason;
      }
      else{
        this.router.navigate(['/admin/serie']);
      }
    });
    this.getSeason();
  }

  getSeason(): void{
    this.seasonService.getById(this.idSeason).subscribe(
      data => this.get(),
      error => this.router.navigate(['/admin/serie'])
    );
  }

  get(): void{
    this.chapterService.getBySeason(this.idSeason).subscribe(
      data => {
        this.chapters = data;
      },
      error => {
        M.toast({html: "No se pudieron traer los datos"})
      }
    );
  }

  resetForm(): void{
    this.urlVideo = "";
  }

  save(): void{
    this.chapterService.save(this.urlVideo, this.idSeason).subscribe(
      data => {
        this.get();
        this.resetForm();
      },
      error => M.toast({html: error.console.message})
    );
  }

  question(chapter: Chapter){
    let isDelete: Boolean = window.confirm("Seguro de querer eliminar este capitulo?" );
    
    if(isDelete){ 
      this.chapterService.delete(this.idSeason, chapter.id).subscribe(
        data =>{
          M.toast({html:  'Capitulo Eliminado'});
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

}
