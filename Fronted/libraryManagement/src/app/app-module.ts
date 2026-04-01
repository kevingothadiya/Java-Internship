import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { HttpClientModule } from '@angular/common/http';
import { Library } from './library/library';
import { CreateBook } from './create-book/create-book';
import { FormsModule } from '@angular/forms';
import { UpdateBook } from './update-book/update-book';

@NgModule({
  declarations: [App, Library, CreateBook, UpdateBook],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
