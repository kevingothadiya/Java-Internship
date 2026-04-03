import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterStudent } from './register-student/register-student';
import { DisplayData } from './display-data/display-data';
import { Login } from './login/login';
import { Contact } from './contact/contact';
import { About } from './about/about';
import { Home } from './home/home';
import { ErrorPage } from './error-page/error-page';
import { Profile } from './profile/profile';

const routes: Routes = [

  {
    path:'',
    component:Home
  },
  {
    path:'register',
    component:RegisterStudent
  },
  {
    path:'display-data',
    component:DisplayData
  },
  {
    path:'login',
    component:Login
  },
  {
    path:'contact',
    component:Contact
  },
  {
    path:'about',
    component:About
  },
  {
    path:'profile/:id',
    component:Profile
  },
  {
    path:'**',
    component:ErrorPage
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
