import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Header } from './header/header';
import { Body } from './body/body';
import { Footer } from './footer/footer';
import { Sidebar } from './sidebar/sidebar';

@NgModule({
  declarations: [App, Header, Body, Footer, Sidebar],
  imports: [BrowserModule, AppRoutingModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
