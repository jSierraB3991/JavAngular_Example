import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenServiceService } from 'src/app/Service/token-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private tokenService: TokenServiceService,
              private router: Router) { }

  ngOnInit(): void {
    if(!this.tokenService.getToken()){
      this.router.navigate(['/serie']);
    }
    else if(this.tokenService.getRole()!=null && this.tokenService.getRole() != "ROLE_ADMIN"){
      this.router.navigate(['/serie']);
    }
  }

  closeSession():void{
    this.tokenService.logOut();
    this.router.navigate(['/login']);
  }

}
