import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ServiceProvider } from '../service/service-provider';

@Component({
  selector: 'app-reset-password',
  standalone: false,
  templateUrl: './reset-password.html',
  styleUrl: './reset-password.css',
})
export class ResetPassword implements OnInit {
  myService = inject(ServiceProvider);
  myRouter = inject(Router);
  route = inject(ActivatedRoute);
  cd = inject(ChangeDetectorRef);

  token = '';
  loading = false;
  success = false;
  alreadyUsed = false;
  errorMsg = '';

  resetForm = new FormGroup(
    {
      newPassword: new FormControl('', [
        Validators.required,
        Validators.pattern(
          '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$',
        ),
      ]),
      confirmPassword: new FormControl('', [Validators.required]),
    },
    { validators: this.passwordMatchValidator },
  );

  get fc() {
    return this.resetForm.controls;
  }

  passwordMatchValidator(group: any) {
    const pass = group.get('newPassword')?.value;
    const confirm = group.get('confirmPassword')?.value;
    return pass === confirm ? null : { mismatch: true };
  }

  ngOnInit() {
    this.token = this.route.snapshot.queryParamMap.get('token') || '';
    if (!this.token) {
      this.errorMsg = 'Invalid or missing reset token.';
    }
  }

  resetPassword() {
    if (this.resetForm.invalid) {
      this.resetForm.markAllAsTouched();
      return;
    }
    this.loading = true;
    this.errorMsg = '';

    this.myService.resetPassword(this.token, this.resetForm.value.newPassword!).subscribe({
      next: (res) => {
        console.log('SUCCESS RESPONSE:', res);

        this.success = true;
        this.loading = false;

        // 👇 Important: reset form
        this.resetForm.reset();

        // 👇 Force UI update (just in case)
        this.cd.detectChanges();

        // Redirect after 3 sec
        setTimeout(() => {
          this.myRouter.navigate(['/login']);
        }, 3000);
      },
      error: (err) => {
        const msg: string = typeof err.error === 'string' ? err.error : '';
        if (msg.toLowerCase().includes('invalid') || msg.toLowerCase().includes('expired')) {
          this.alreadyUsed = true;
        } else {
          this.errorMsg = msg || 'Reset failed. Please try again.';
        }
        this.loading = false;
        this.cd.detectChanges();
      },
    });
  }
}
