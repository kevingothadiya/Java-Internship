import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-get-user',
  standalone: false,
  templateUrl: './get-user.html',
  styleUrl: './get-user.css',
})
export class GetUser implements OnInit{
  service = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  users: any[] = [];
  selectedUserId: number | null = null;

  // Reactive form for updating user
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

  // Load user list
  loadUsers() {
    this.service.getUser().subscribe({
      next: (resp) =>{
        this.users = resp
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err)
      }
    });
  }

  // Open update modal and populate form
  openUpdateModal(user: any) {
    this.selectedUserId = user.id;

    this.updateForm.patchValue({
      name: user.name,
      email: user.email,
      role: user.role,
      phoneNum: user.phoneNum,
      status: user.status
    });

    const modalEl = document.getElementById('editUserModal'); // match HTML id
  if (modalEl) {
    const modal = new (window as any).bootstrap.Modal(modalEl);
    modal.show();
  } else {
    console.error('Modal element not found!');
  }
  }

  // Update user
  updateUser() {
    if (this.updateForm.invalid || this.selectedUserId === null) return;

    this.service.updateUser(this.selectedUserId, this.updateForm.value)
      .subscribe({
        next: () => {
          alert("User updated successfully");

          // Close modal
          const modalEl = document.getElementById('editUserModal'); 
          const modal = (window as any).bootstrap.Modal.getInstance(modalEl);
          modal?.hide();

          // Update local user array
          const index = this.users.findIndex(u => u.id === this.selectedUserId);
          if (index !== -1) {
            this.users[index] = { ...this.users[index], ...this.updateForm.value };
          }

          this.loadUsers();
          this.cdr.detectChanges();
        },
        error: (err) => console.error(err)
      });
  }

  // Delete user
  deleteUser(id: number) {
    if (!confirm("Are you sure you want to delete this user?")) return;

    this.service.deleteUserById(id).subscribe({
      next: () => {
        alert("User deleted ");
        this.users = this.users.filter(u => u.id !== id);
      },
      error: (err) => console.error(err)
    });
  }

}
