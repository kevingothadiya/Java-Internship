import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { PropertyBinding } from './property-binding/property-binding';
import { ClassBinding } from './class-binding/class-binding';
import { StyleBinding } from './style-binding/style-binding';
import { EventBinding } from './event-binding/event-binding';
import { TwoWayBinding } from './two-way-binding/two-way-binding';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [App, PropertyBinding, ClassBinding, StyleBinding, EventBinding, TwoWayBinding],
  imports: [BrowserModule, AppRoutingModule,FormsModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {
}
