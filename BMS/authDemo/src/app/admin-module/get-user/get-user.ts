import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-get-user',
  standalone: false,
  templateUrl: './get-user.html',
  styleUrl: './get-user.css',
})
export class GetUser implements OnInit {
  service = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  users: any[] = [];
  selectedUserId: number | null = null;

  // Pagination state
  currentPage = 0;
  pageSize = 5;
  totalElements = 0;
  totalPages = 0;
  pageSizeOptions = [5, 10, 25];

  updateForm: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    role: new FormControl('', Validators.required),
    phoneNum: new FormControl('', Validators.required),
    status: new FormControl('', Validators.required)
  });

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.service.getUsersPaged(this.currentPage, this.pageSize).subscribe({
      next: (resp) => {
        this.users = resp.content;
        this.totalElements = resp.page?.totalElements ?? resp.totalElements ?? 0;
        this.totalPages = resp.page?.totalPages ?? resp.totalPages ?? 0;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  // Pagination controls
  goToPage(page: number) {
    if (page < 0 || page >= this.totalPages) return;
    this.currentPage = page;
    this.loadUsers();
  }

  onPageSizeChange(event: Event) {
    this.pageSize = Number((event.target as HTMLSelectElement).value);
    this.currentPage = 0;
    this.loadUsers();
  }

  get pageNumbers(): number[] {
    const total = this.totalPages;
    const current = this.currentPage;
    const delta = 1; // pages around current
    const pages: number[] = [];

    for (let i = 0; i < total; i++) {
      if (i === 0 || i === total - 1 || (i >= current - delta && i <= current + delta)) {
        pages.push(i);
      }
    }
    return pages;
  }

  get showingFrom(): number {
    return this.totalElements === 0 ? 0 : this.currentPage * this.pageSize + 1;
  }

  get showingTo(): number {
    return Math.min((this.currentPage + 1) * this.pageSize, this.totalElements);
  }

  openUpdateModal(user: any) {
    this.selectedUserId = user.id;
    this.updateForm.patchValue({
      name: user.name,
      email: user.email,
      role: user.role,
      phoneNum: user.phoneNum,
      status: user.status
    });
    const modalEl = document.getElementById('editUserModal');
    if (modalEl) {
      const modal = new (window as any).bootstrap.Modal(modalEl);
      modal.show();
    }
  }

  updateUser() {
    if (this.updateForm.invalid || this.selectedUserId === null) return;
    this.service.updateUser(this.selectedUserId, this.updateForm.value).subscribe({
      next: () => {
        alert('User updated successfully');
        const modalEl = document.getElementById('editUserModal');
        const modal = (window as any).bootstrap.Modal.getInstance(modalEl);
        modal?.hide();
        this.loadUsers();
      },
      error: (err) => console.error(err)
    });
  }

  deleteUser(id: number) {
    if (!confirm('Are you sure you want to delete this user?')) return;
    this.service.deleteUserById(id).subscribe({
      next: () => {
        alert('User deleted');
        // If last item on page, go back one page
        if (this.users.length === 1 && this.currentPage > 0) this.currentPage--;
        this.loadUsers();
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }
}
