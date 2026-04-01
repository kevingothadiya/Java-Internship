import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { RegisterStudent } from './register-student/register-student';
import { ReactiveFormsModule } from '@angular/forms';
import { DisplayData } from './display-data/display-data';
import { Login } from './login/login';
import { ErrorPage } from './error-page/error-page';
import { Contact } from './contact/contact';
import { About } from './about/about';
import { Home } from './home/home';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@NgModule({
  declarations: [App, RegisterStudent, DisplayData, Login, ErrorPage, Contact, About, Home],
  imports: [BrowserModule, AppRoutingModule, ReactiveFormsModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {}
