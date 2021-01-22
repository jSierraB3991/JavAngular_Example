import { Component, OnInit } from '@angular/core';
import {Validators, FormGroup, FormBuilder, FormControl} from "@angular/forms";
import { Router } from '@angular/router';
import { LoginRequest } from 'src/app/models/LoginRequest';
import { SerieServiceService } from 'src/app/Service/serie-service.service';
import { TokenServiceService } from 'src/app/Service/token-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public errorMessage: string = "";
  public email: string = "";
  public  password: string = "";
  submitted: Boolean = false;
  isLoging: Boolean = false;
  hasError: Boolean = false;
  isLoginSuccessfull: Boolean = false;
  loginReq: any;
  
  constructor(private authenticationService: SerieServiceService,
              private formBuilder: FormBuilder,
              private tokenService: TokenServiceService,
              private router: Router) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){
      this.goPrincipal();
    }
  }

  goPrincipal():void{
    this.router.navigate(['/serie']);
  }
  
  goAdmin():void{
    this.router.navigate(['/admin/category']);
  }

  register():void{
    this.router.navigate(['/register']);
  }

  onLogin(): void{
    this.hasError = false; 
    this.submitted = true;
    this.authenticationService.login({
      email: this.email,
      password: this.password
    }).subscribe(
      data => {
        this.submitted = false;
        this.isLoginSuccessfull = true;
        this.isLoging = true;
        this.tokenService.setToken(data.token);
        this.tokenService.setUserName(data.userName);
        this.tokenService.setTokenType(data.bearer);
        var isAdmin: Boolean = false;
        data.roles.forEach(rol => {
          this.tokenService.setRole(rol.authority)
          if(rol.authority == "ROLE_ADMIN"){
            isAdmin = true;
          }
        });
        if(isAdmin) {this.goAdmin();}
        else{ this.goPrincipal();}
      },
      error => {
        this.hasError = true;
        this.isLoginSuccessfull = false;
        this.isLoging = false;
        this.submitted = false;
        this.errorMessage = error.error.message;  
      }
    )
  }

  cancel(): void{
    this.goPrincipal();
  }

}
