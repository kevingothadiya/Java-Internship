import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';

@Component({
  selector: 'app-blood-request-details',
  standalone: false,
  templateUrl: './blood-request-details.html',
  styleUrl: './blood-request-details.css',
})
export class BloodRequestDetails implements OnInit {

  myService = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  requests: any[] = [];
  currentPage = 0;
  pageSize = 5;
  totalElements = 0;
  totalPages = 0;
  pageSizeOptions = [5, 10, 25];

  ngOnInit(): void { this.loadRequests(); }

  loadRequests() {
    this.myService.getBloodRequestsPaged(this.currentPage, this.pageSize).subscribe({
      next: (resp) => {
        this.requests = resp.content;
        this.totalElements = resp.totalElements;
        this.totalPages = resp.totalPages;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  approve(id: number) {
    this.myService.approveBloodRequest(id).subscribe({
      next: () => { alert('Blood Request Approved'); this.loadRequests(); },
      error: (err) => {
        const msg = typeof err.error === 'string' ? err.error : (err.error?.message || 'Approval failed');
        alert(msg);
      }
    });
  }

  reject(id: number) {
    this.myService.rejectBloodRequest(id).subscribe({
      next: () => { alert('Blood Request Rejected'); this.loadRequests(); },
      error: (err) => {
        const msg = typeof err.error === 'string' ? err.error : (err.error?.message || 'Rejection failed');
        alert(msg);
      }
    });
  }

  goToPage(page: number) {
    if (page < 0 || page >= this.totalPages) return;
    this.currentPage = page;
    this.loadRequests();
  }

  onPageSizeChange(event: Event) {
    this.pageSize = Number((event.target as HTMLSelectElement).value);
    this.currentPage = 0;
    this.loadRequests();
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
