import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SerieServiceService } from 'src/app/Service/serie-service.service';
import { TokenServiceService } from 'src/app/Service/token-service.service';

@Component({
  selector: 'app-register-component',
  templateUrl: './register-component.component.html',
  styleUrls: ['./register-component.component.css']
})
export class RegisterComponentComponent implements OnInit {

  hasError: Boolean = false;
  submitted: Boolean = false;
  email: string = "";
  password: string = "";
  firstName: string = "";
  lastName: string = "";
  address: string = "";
  cellPhone: string = "";
  errorMessage: string = ""; 

  constructor(private authenticationService: SerieServiceService,
              private tokenService: TokenServiceService,
              private router: Router) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.goPrincipal();
    }
  }

  onRegister(): void{
    
    this.submitted = true;
    this.authenticationService.register({
      email: this.email,
      password: this.password,
      firstName: this.firstName,
      lastName: this.lastName,
      address: this.address,
      cellPhone: this.cellPhone
    }).subscribe(
      data => {
        this.submitted = false;
        console.log(data);
        this.authenticationService.login({
          email: this.email,
          password: this.password
        }).subscribe(
          data => {
            this.submitted = false;
            console.log(data);
            this.tokenService.setToken(data.token);
            this.tokenService.setUserName(data.userName);
            this.tokenService.setTokenType(data.bearer);
            this.goPrincipal();
          },
          error => {
            this.hasError = true;
            this.submitted = false;
            console.log(error.error.message);
            this.errorMessage = error.error.message;  
          }
        );
      },
      error => {
        this.hasError = true;
        this.submitted = false;
        console.log(error.error.message);
        this.errorMessage = error.error.message;  
      }
    );
  }

  goPrincipal():void{
    this.router.navigate(['/serie']);
  }

  cancel():void{
    this.router.navigate(['/login']);
  }

}
