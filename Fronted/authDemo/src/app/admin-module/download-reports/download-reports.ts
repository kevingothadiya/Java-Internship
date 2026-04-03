import { Component, inject } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';

@Component({
  selector: 'app-download-reports',
  standalone: false,
  templateUrl: './download-reports.html',
  styleUrl: './download-reports.css',
})
export class DownloadReports {

  myService = inject(ServiceProvider);

  downloadUserExcel() {
  this.myService.downloadUserExcel().subscribe({
    next: (resp: Blob) => {
      const url = window.URL.createObjectURL(resp);
      const a = document.createElement('a');
      a.href = url;
      a.download = 'Users.xlsx';
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    },
    error: (err) => console.error(err)
  });
}
}
