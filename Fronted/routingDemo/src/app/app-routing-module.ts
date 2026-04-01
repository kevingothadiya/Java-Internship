import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Home } from './home/home';
import { Dashbord } from './dashbord/dashbord';
import { About } from './about/about';
import { HelpDesk } from './help-desk/help-desk';
import { ErrorPage } from './error-page/error-page';

const routes: Routes = [
  {
    path:'',
    component:Home
  },
  {
    path:'home',
    component:Home
  },
  {
    path:'dashboard',
    component:Dashbord
  },
  {
    path:'about',
    component:About
  },
  {
    path:'help',
    component:HelpDesk
  },
  {
    path:'**',
    component:ErrorPage
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
