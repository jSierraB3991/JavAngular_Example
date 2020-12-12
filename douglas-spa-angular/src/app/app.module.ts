import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from './components/app/app-routing.module';
import { AppComponent } from './components/app/app.component';
import { SeriesComponent } from './components/series/series.component';
import { SerieServiceService } from './Service/serie-service.service';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SerieComponent } from './components/serie/serie.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'serie'
  },
  {
    path: 'serie',
    component: SeriesComponent
  },
  {
    path: 'details',
    component: SerieComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
];

@NgModule({
  declarations: [
    AppComponent,
    SeriesComponent,
    LoginComponent,
    SerieComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes,{
      useHash: true
    })
  ],
  providers: [SerieServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
