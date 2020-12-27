import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/Category';
import { CategoryService } from 'src/app/Service/category-service.service';

declare var M: any;
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  name: String = "";
  id: number = 0;
  categories: Array<Category> = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.get();
  }

  resetForm(): void{
    this.name = "";
    this.id = 0;
  }

  save(): void{
    if(this.id != 0){
      this.udpate();
    }
    else {
      this.categoryService.postCategories({ 
        name: this.name,
        id: 0
      }).subscribe(
        data =>{
          M.toast({html: 'Categoria '+ this.name +' Guardada'});
          this.resetForm();
          this.get();
        },
        error => M.toast({html: error.error.message})
        );
    }
  }

  get(): void{
    this.categoryService.getCategories().subscribe(
      data => this.categories = data,
      error => {
        M.toast({html: 'No se pudo traer los datos'});
    })
  }

  edit(category: Category):void{
    this.name = category.name;
    this.id = category.id;
  }

  udpate(): void{
    this.categoryService.putCategories({ 
      name: this.name,
      id: this.id
    }).subscribe(
      data =>{
        M.toast({html: 'Categoria Actualizada'});
        this.resetForm();
        this.get();
      },
      error => M.toast({html: error.error.message})
      );
  }
  
  question(category: Category){
    let isDelete: Boolean = window.confirm("Seguro de querer eliminar la categoria " + category.name);
    
    if(isDelete){ 
      this.categoryService.deleteCategories(category).subscribe(
        data => {
          M.toast({html: 'Categoria ' + this.name + ' Eliminada'});
          this.resetForm();
          this.get();
        },
        error => {
          M.toast({html: error.error.message});
          this.resetForm();
      });
      this.resetForm();
    }
    else{
      this.resetForm();
    }
  }

}
