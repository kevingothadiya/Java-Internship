import { Component, OnInit, signal } from '@angular/core';
import { StorageService } from './service/storage-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App{
  protected readonly title = signal('authDemo');

}
