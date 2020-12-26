import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/Category';
import { Serie } from 'src/app/models/Serie';
import { CategoryService } from 'src/app/Service/category-service.service';
import { SerieAdminService } from 'src/app/Service/serie-admin-service.service';

declare var M: any;

@Component({
  selector: 'app-serie',
  templateUrl: './serie.component.html',
  styleUrls: ['./serie.component.css']
})
export class SerieAdminComponent implements OnInit {
  name: string = "";
  remarks: string = "";
  categoryid: number = 0;
  category: Category = new Category;
  id: number = 0;
  series: Array<Serie> = [];
  categories: Array<Category> = [];
  imageurl: String = "";

  constructor(private serieService: SerieAdminService,
            private categoryService: CategoryService,
            private router: Router) { }

  ngOnInit(): void {
    this.get();
    this.getCategories();
  }

  getCategories(): void{
    this.categoryService.getCategories().subscribe(
      data => {
        this.categories = data
      },
      error => {
        console.log(error);
        M.toast({html: 'No se pudieron traer las categorias'});
      }
    );
  }

  resetForm(): void{
    this.name = "";
    this.id = 0;
    this.remarks = "";
    this.categoryid = 0;
    this.category = new Category;
  }

  edit(serie: Serie){
    console.log(serie);
    this.id = serie.id;
    this.name = serie.name;
    this.category = serie.category;
    this.remarks = serie.remarks;
    this.categoryid = serie.category.id;
    this.imageurl = serie.firstImage;
  }

  save():void{
    this.category = { id: this.categoryid, name: this.categories.filter(cat => cat.id == this.categoryid)[0].name};
    if(this.id != 0){
      this.update();
    }
    else {
      this.serieService.post({ 
        name: this.name,
        id: 0,
        category: this.category,
        remarks: this.remarks,
        firstImage: "",
        images: [],
        seasons: []
      }).subscribe(
        data =>{
          console.log(data);
          M.toast({html: 'Serie '+ this.name +' Guardada'});
          this.resetForm();
          this.get();
        },
        error => M.toast({html: error.error.message})
        );
    }
  }

  goSeason(idSerie: number): void{
    this.router.navigate(['/admin/season'], { queryParams: { idSerie: idSerie } });

  }

  get(): void{
    this.serieService.get().subscribe(data => {
        console.log(data);
        this.series = data;
    },error => {
      console.log(error);
      M.toast({html: 'No se pudo traer los datos'});
    })
  }

  update(): void{
    this.serieService.put({
      name: this.name,
      id: this.id,
      category: this.category,
      remarks: this.remarks,
      firstImage: "",
      images: [],
      seasons: []
    }).subscribe(
      data =>{
        console.log(data);
        M.toast({html: 'Serie Actualizada'});
        this.resetForm();
        this.get();
      },
      error => M.toast({html: error.error.message})
      );
  }

  question(serie: Serie){
    let isDelete: Boolean = window.confirm("Seguro de querer eliminar la serie " + serie.name);
    
    if(isDelete){ 
      this.serieService.delete(serie.id).subscribe(
        data =>{
          M.toast({html: 'Serie ' + serie.name + ' Eliminada'});
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
