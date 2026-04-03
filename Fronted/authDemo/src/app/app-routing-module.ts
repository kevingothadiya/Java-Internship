import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Home } from './home/home';
import { Login } from './login/login';
import { Registration } from './registration/registration';
import { Dashboard } from './dashboard/dashboard';
import { authGuard } from './guard/auth-guard';
import { AdminDashboard } from './admin-module/admin-dashboard/admin-dashboard';
import { GetUser } from './admin-module/get-user/get-user';
import { ForgotPassword } from './forgot-password/forgot-password';
import { DownloadReports } from './admin-module/download-reports/download-reports';
import { ErrorPage } from './error-page/error-page';
import { DonationDetails } from './admin-module/donation-details/donation-details';
import { DonorDashboard } from './donor-module/donor-dashboard/donor-dashboard';

const routes: Routes = [
  {
    path:'',
    component:Home
  },
  {
    path:'login',
    component:Login
  },
  {
    path:'registration',
    component:Registration
  },
  { 
    path: 'dashboard', 
    component: Dashboard, 
    canActivate: [authGuard] 
  },
  {
    path:'admin',
    component:AdminDashboard,
    canActivate:[authGuard],
    children:[
      {
        path:'user',
        component:GetUser
      },
      {
        path:'reports',
        component:DownloadReports
      },
      {
        path:'donation',
        component:DonationDetails
      }
    ]
  },
  {
    path:'donor',
    component:DonorDashboard,
    canActivate:[authGuard]
  },
  {
    path:'forgot-password',
    component:ForgotPassword
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
