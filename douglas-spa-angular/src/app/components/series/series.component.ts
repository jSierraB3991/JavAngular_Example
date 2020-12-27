import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/Category';
import { Serie } from 'src/app/models/Serie';
import { SerieServiceService } from 'src/app/Service/serie-service.service';
import { TokenServiceService } from 'src/app/Service/token-service.service';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.css']
})
export class SeriesComponent implements OnInit {
  series: Array<Serie> = [];
  isNotLogin: Boolean = true;
  categories: Array<Category> = [];
  isAdmin: Boolean = false;

  constructor(private service: SerieServiceService,
            private tokenService: TokenServiceService,
            private router: Router) { 
  }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.isNotLogin = false;
      if(this.tokenService.getRole()!=null && this.tokenService.getRole()=="ROLE_ADMIN"){
        this.isAdmin = true;
      }
    }

    this.service.getCategories().subscribe(data => {
      this.categories = data;
    });

    this.service.getSeries().subscribe(data => {
      this.series = data.data;
    });
  }

  closeSession(): void{
    this.tokenService.logOut();
    this.router.navigate(['/login']);
  }

}
