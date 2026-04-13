import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServiceProvider } from '../service/service-provider';

@Component({
  selector: 'app-forgot-password',
  standalone: false,
  templateUrl: './forgot-password.html',
  styleUrl: './forgot-password.css',
})
export class ForgotPassword {
  myService = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  loading = false;
  emailSent = false;
  errorMsg = '';
  sentToEmail = '';

  emailForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
    ]),
  });

  get fc() { return this.emailForm.controls; }

  sendResetLink() {
    if (this.emailForm.invalid) {
      this.emailForm.markAllAsTouched();
      this.cdr.detectChanges();
      return;
    }

    this.loading = true;
    this.errorMsg = '';
    this.cdr.detectChanges();

    const email = this.emailForm.value.email!;

    this.myService.sendResetLink(email).subscribe({
      next: () => {
        this.sentToEmail = email;
        this.emailSent = true;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        const msg: string = typeof err.error === 'string' ? err.error : '';
        this.errorMsg = msg.includes('No account')
          ? 'Your email id is not available. Please register first.'
          : (msg || 'Something went wrong. Please try again.');
        this.loading = false;
        this.cdr.detectChanges();
      },
    });
  }
}
