import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { App } from './app';
import { Home } from './home/home';
import { Login } from './login/login';
import { Registration } from './registration/registration';
import { Dashboard } from './dashboard/dashboard';
import { LoginNavbar } from './navbar/login-navbar/login-navbar';
import { AdminDashboard } from './admin-module/admin-dashboard/admin-dashboard';
import { GetUser } from './admin-module/get-user/get-user';
import { authInterceptor } from './service/auth-interceptor';
import { ForgotPassword } from './forgot-password/forgot-password';
import { DownloadReports } from './admin-module/download-reports/download-reports';
import { ErrorPage } from './error-page/error-page';
import { DonationDetails } from './admin-module/donation-details/donation-details';
import { DonorDashboard } from './donor-module/donor-dashboard/donor-dashboard';
import { DonationRequest } from './donor-module/donation-request/donation-request';
import { DonorRegistration } from './donor-module/donor-registration/donor-registration';
import { DonorHistory } from './donor-module/donor-history/donor-history';
import { DonorProfile } from './donor-module/donor-profile/donor-profile';
import { HospitalDashboard } from './hospital-module/hospital-dashboard/hospital-dashboard';
import { HospitalRegister } from './hospital-module/hospital-register/hospital-register';
import { BloodRequestHistory } from './hospital-module/blood-request-history/blood-request-history';
import { HospitalProfile } from './hospital-module/hospital-profile/hospital-profile';
import { RequestBlood } from './hospital-module/request-blood/request-blood';

@NgModule({
  declarations: [
    App,
    Home,
    Login,
    Registration,
    Dashboard,
    LoginNavbar,
    AdminDashboard,
    GetUser,
    ForgotPassword,
    DownloadReports,
    ErrorPage,
    DonationDetails,
    DonorDashboard,
    DonationRequest,
    DonorRegistration,
    DonorHistory,
    DonorProfile,
    HospitalDashboard,
    HospitalRegister,
    BloodRequestHistory,
    HospitalProfile,
    RequestBlood,
  ],
  imports: [BrowserModule, AppRoutingModule, ReactiveFormsModule, FormsModule],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptors([authInterceptor])),
  ],
  bootstrap: [App],
})
export class AppModule {}
