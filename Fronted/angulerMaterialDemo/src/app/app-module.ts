import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { ButtonToggle } from './button-toggle/button-toggle';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { DaterPicker } from './dater-picker/dater-picker';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {provideNativeDateAdapter} from '@angular/material/core';

@NgModule({
  declarations: [App, ButtonToggle, DaterPicker],
  imports: [BrowserModule, AppRoutingModule, MatButtonToggleModule,MatDatepickerModule,MatInputModule,MatFormFieldModule],
  bootstrap: [App],
  providers: [provideNativeDateAdapter()]
})
export class AppModule {}
