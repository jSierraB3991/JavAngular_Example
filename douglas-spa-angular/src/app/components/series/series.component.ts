import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/Category';
import { Serie } from 'src/app/models/Serie';
import { SerieServiceService } from 'src/app/Service/serie-service.service';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.css']
})
export class SeriesComponent implements OnInit {
  series: Array<Serie> = [];
  categories: Array<Category> = [];

  constructor(private service: SerieServiceService) { 
  }

  ngOnInit(): void {
    this.service.getCategories().subscribe(data => {
      console.log(data);
      this.categories = data;
    });

    this.service.getSeries().subscribe(data => {
      console.log(data.data);
      this.series = data.data;
    });
  }

}
