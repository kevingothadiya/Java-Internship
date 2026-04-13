import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';

interface ReportOption {
  label: string;
  value: string;
  fileName: string;
  icon: string;
  description: string;
}

@Component({
  selector: 'app-download-reports',
  standalone: false,
  templateUrl: './download-reports.html',
  styleUrl: './download-reports.css',
})
export class DownloadReports {

  myService = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  selectedReport = '';
  loading = false;
  errorMsg = '';
  successMsg = '';

  reports: ReportOption[] = [
    {
      label: 'User Details',
      value: 'user',
      fileName: 'Users.xlsx',
      icon: 'bi-people-fill',
      description: 'All registered users with their roles and status'
    },
    {
      label: 'Hospital Details',
      value: 'hospital',
      fileName: 'Hospitals.xlsx',
      icon: 'bi-hospital-fill',
      description: 'All registered hospitals and their information'
    },
    {
      label: 'Blood Stock Details',
      value: 'blood-stock',
      fileName: 'Blood_Stock.xlsx',
      icon: 'bi-droplet-fill',
      description: 'Current blood stock levels by blood group'
    },
    {
      label: 'Donor Details',
      value: 'donor-details',
      fileName: 'Donor_Details.xlsx',
      icon: 'bi-heart-pulse-fill',
      description: 'All donor profiles and donation history'
    },
  ];

  get selectedOption(): ReportOption | undefined {
    return this.reports.find(r => r.value === this.selectedReport);
  }

  download() {
    if (!this.selectedReport) return;

    this.loading = true;
    this.errorMsg = '';
    this.successMsg = '';
    this.cdr.detectChanges();

    const option = this.selectedOption!;
    let request$;

    switch (this.selectedReport) {
      case 'user':         request$ = this.myService.downloadUserExcel(); break;
      case 'hospital':     request$ = this.myService.downloadHospitalExcel(); break;
      case 'blood-stock':  request$ = this.myService.downloadBloodStockExcel(); break;
      case 'donor-details':request$ = this.myService.downloadDonorDetailsExcel(); break;
      default: return;
    }

    request$.subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = option.fileName;
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
        window.URL.revokeObjectURL(url);
        this.successMsg = `"${option.label}" downloaded successfully.`;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        this.errorMsg = 'Download failed. Please try again.';
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }
}
