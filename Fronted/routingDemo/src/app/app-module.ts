import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Dashbord } from './dashbord/dashbord';
import { Home } from './home/home';
import { About } from './about/about';
import { HelpDesk } from './help-desk/help-desk';
import { ErrorPage } from './error-page/error-page';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

@NgModule({
  declarations: [App, Dashbord, Home, About, HelpDesk, ErrorPage],
  imports: [BrowserModule, AppRoutingModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
  schemas:[CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {}
