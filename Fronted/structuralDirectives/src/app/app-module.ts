import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { IfDirective } from './if-directive/if-directive';
import { FormsModule } from '@angular/forms';
import { SwitchDirective } from './switch-directive/switch-directive';
import { ForDirectives } from './for-directives/for-directives';

@NgModule({
  declarations: [App, IfDirective, SwitchDirective, ForDirectives],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
