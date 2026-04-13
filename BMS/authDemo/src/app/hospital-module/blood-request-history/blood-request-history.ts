import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { StorageService } from '../../service/storage-service';

@Component({
  selector: 'app-blood-request-history',
  standalone: false,
  templateUrl: './blood-request-history.html',
  styleUrl: './blood-request-history.css',
})
export class BloodRequestHistory implements OnInit {

  myService = inject(ServiceProvider);
  myStorage = inject(StorageService);
  cdr = inject(ChangeDetectorRef);

  hospitalId = 0;
  history: any[] = [];

  currentPage = 0;
  pageSize = 5;
  totalElements = 0;
  totalPages = 0;
  pageSizeOptions = [5, 10, 25];

  ngOnInit(): void {
    const id = this.myStorage.getItem('hospitalId');
    if (id) {
      this.hospitalId = Number(id);
      this.loadHistory();
    }
  }

  loadHistory() {
    this.myService.getBloodRequestHistoryPaged(this.hospitalId, this.currentPage, this.pageSize).subscribe({
      next: (resp) => {
        this.history = resp.content;
        this.totalElements = resp.page?.totalElements ?? resp.totalElements ?? 0;
        this.totalPages = resp.page?.totalPages ?? resp.totalPages ?? 0;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  goToPage(page: number) {
    if (page < 0 || page >= this.totalPages) return;
    this.currentPage = page;
    this.loadHistory();
  }

  onPageSizeChange(event: Event) {
    this.pageSize = Number((event.target as HTMLSelectElement).value);
    this.currentPage = 0;
    this.loadHistory();
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
