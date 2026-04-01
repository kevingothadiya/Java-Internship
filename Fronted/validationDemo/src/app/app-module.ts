import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { RegisterStudent } from './register-student/register-student';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { OperationAtStudent } from './operation-at-student/operation-at-student';

@NgModule({
  declarations: [App, RegisterStudent, OperationAtStudent],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
