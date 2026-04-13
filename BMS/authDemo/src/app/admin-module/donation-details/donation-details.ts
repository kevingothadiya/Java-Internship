import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';

@Component({
  selector: 'app-donation-details',
  standalone: false,
  templateUrl: './donation-details.html',
  styleUrl: './donation-details.css',
})
export class DonationDetails implements OnInit {

  myService = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  donations: any[] = [];
  currentPage = 0;
  pageSize = 5;
  totalElements = 0;
  totalPages = 0;
  pageSizeOptions = [5, 10, 25];

  ngOnInit(): void { this.loadDonations(); }

  loadDonations() {
    this.myService.getDonationsPaged(this.currentPage, this.pageSize).subscribe({
      next: (resp) => {
        this.donations = resp.content;
        this.totalElements = resp.page?.totalElements ?? resp.totalElements ?? 0;
        this.totalPages = resp.page?.totalPages ?? resp.totalPages ?? 0;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  approve(id: number) {
    this.myService.approveDonationRequest(id).subscribe({
      next: () => { alert('Donation Request Approved'); this.loadDonations(); },
      error: (err) => alert(err.error || 'Approval failed')
    });
  }

  reject(id: number) {
    this.myService.rejectDonationRequest(id).subscribe({
      next: () => { alert('Donation Request Rejected'); this.loadDonations(); },
      error: (err) => alert(err.error || 'Rejection failed')
    });
  }

  goToPage(page: number) {
    if (page < 0 || page >= this.totalPages) return;
    this.currentPage = page;
    this.loadDonations();
  }

  onPageSizeChange(event: Event) {
    this.pageSize = Number((event.target as HTMLSelectElement).value);
    this.currentPage = 0;
    this.loadDonations();
  }

  get pageNumbers(): number[] {
    const pages: number[] = [];
    for (let i = 0; i < this.totalPages; i++) {
      if (i === 0 || i === this.totalPages - 1 || (i >= this.currentPage - 1 && i <= this.currentPage + 1))
        pages.push(i);
    }
    return pages;
  }

  get showingFrom(): number { return this.totalElements === 0 ? 0 : this.currentPage * this.pageSize + 1; }
  get showingTo(): number { return Math.min((this.currentPage + 1) * this.pageSize, this.totalElements); }
}
