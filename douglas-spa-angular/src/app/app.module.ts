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
import { RegisterComponentComponent } from './components/register-component/register-component.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin/admin.component';
import { CategoryComponent } from './admin/category/category.component';
import { SerieAdminComponent } from './admin/serie/serie.component';
import { SeasonAdminComponent } from './admin/season-admin/season-admin.component';
import { CaptherSeasonComponent } from './admin/capther-season/capther-season.component';
import { PlayVideoComponent } from './component/play-video/play-video.component';

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
  {
    path: 'register',
    component: RegisterComponentComponent
  },
  {
    path: 'admin/category',
    component: CategoryComponent
  },
  {
    path: 'admin/serie',
    component: SerieAdminComponent
  },
  {
    path: 'admin/season',
    component: SeasonAdminComponent
  },
  {
    path: 'admin/chapter',
    component: CaptherSeasonComponent
  },
];

@NgModule({
  declarations: [
    AppComponent,
    SeriesComponent,
    LoginComponent,
    SerieComponent,
    RegisterComponentComponent,
    AdminComponent,
    CategoryComponent,
    SerieAdminComponent,
    SeasonAdminComponent,
    CaptherSeasonComponent,
    PlayVideoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forRoot(routes,{
      useHash: true
    })
  ],
  providers: [SerieServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
