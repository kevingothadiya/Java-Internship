import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { PipeDemo } from './pipe-demo/pipe-demo';
import { PowerPipe } from './pipe/power-pipe';
import { FectorPipePipe } from './pipe/fector-pipe-pipe';

@NgModule({
  declarations: [App, PipeDemo, PowerPipe, FectorPipePipe],
  imports: [BrowserModule, AppRoutingModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
