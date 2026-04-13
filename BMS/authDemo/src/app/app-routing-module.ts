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
import { DonorRegistration } from './donor-module/donor-registration/donor-registration';
import { DonorHistory } from './donor-module/donor-history/donor-history';
import { DonorProfile } from './donor-module/donor-profile/donor-profile';
import { DonationRequest } from './donor-module/donation-request/donation-request';
import { HospitalDashboard } from './hospital-module/hospital-dashboard/hospital-dashboard';
import { HospitalRegister } from './hospital-module/hospital-register/hospital-register';
import { BloodRequestHistory } from './hospital-module/blood-request-history/blood-request-history';
import { HospitalProfile } from './hospital-module/hospital-profile/hospital-profile';
import { RequestBlood } from './hospital-module/request-blood/request-blood';
import { ResetPassword } from './reset-password/reset-password';
import { BloodRequestDetails } from './admin-module/blood-request-details/blood-request-details';
import { BloodStockDetails } from './admin-module/blood-stock-details/blood-stock-details';

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
      },
      {
        path:'blood-requests',
        component:BloodRequestDetails
      },
      {
        path:'blood-stock',
        component:BloodStockDetails
      }
    ]
  },
  {
    path:'donor',
    component:DonorDashboard,
    canActivate:[authGuard],
    children:[
      {
        path:'donation-request',
        component:DonationRequest
      },
      {
        path:'donor-history',
        component:DonorHistory
      },
      {
        path:'donor-profile',
        component:DonorProfile
      }
    ]
  },
  {
    path:'donor-registration',
    component:DonorRegistration,
    canActivate:[authGuard]
  },
  {
    path:'hospital',
    component:HospitalDashboard,
    canActivate:[authGuard],
    children:[
      {
        path:'blood-request-history',
        component:BloodRequestHistory
      },
      {
        path:'profile',
        component:HospitalProfile
      },
      {
        path:'request-blood',
        component:RequestBlood
      }
    ]
  },
  {
    path:'hospital-register',
    component:HospitalRegister,
    canActivate:[authGuard]
  },
  {
    path:'forgot-password',
    component:ForgotPassword
  },
  {
    path:'reset-password',
    component:ResetPassword
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
