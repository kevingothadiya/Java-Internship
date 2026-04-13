import { ChangeDetectorRef, Component, inject, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ServiceProvider } from '../service/service-provider';
import { Router } from '@angular/router';
import { StorageService } from '../service/storage-service';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit, OnDestroy {

  myService = inject(ServiceProvider);
  myStorageService = inject(StorageService);
  myRouter = inject(Router);
  cdr = inject(ChangeDetectorRef);

  lockedUntilMs: number | null = null;
  countdown = 0;
  failedAttempts = 0;
  countdownInterval: any = null;
  errorMsg = '';

  public myLogin = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$')]),
    password: new FormControl('', [Validators.required]),
  });

  get formControl() { return this.myLogin.controls; }

  get isLocked(): boolean {
    return this.lockedUntilMs !== null && Date.now() < this.lockedUntilMs;
  }

  get countdownDisplay(): string {
    const mins = Math.floor(this.countdown / 60);
    const secs = this.countdown % 60;
    return `${String(mins).padStart(2, '0')}:${String(secs).padStart(2, '0')}`;
  }

  ngOnInit() {
    // Page loads clean — no pre-fill, no lock applied automatically
    // Only check lock when user finishes typing an email (on blur or value change with valid email)
    this.myLogin.get('email')!.valueChanges.subscribe((val) => {
      // Clear previous lock/error state when email changes
      this.clearLockState();

      // If typed email looks valid, check its lock status from DB
      const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
      if (val && emailPattern.test(val)) {
        this.checkLockStatus(val);
      }
      this.cdr.detectChanges();
    });
  }

  private clearLockState() {
    clearInterval(this.countdownInterval);
    this.countdownInterval = null;
    this.lockedUntilMs = null;
    this.countdown = 0;
    this.failedAttempts = 0;
    this.errorMsg = '';
    this.cdr.detectChanges();
  }

  private checkLockStatus(email: string) {
    this.myService.getLockStatus(email).subscribe({
      next: (status: any) => {
        this.failedAttempts = status.failedAttempts;
        if (status.locked && status.lockRemainingSeconds > 0) {
          this.lockedUntilMs = Date.now() + (status.lockRemainingSeconds * 1000);
          this.countdown = status.lockRemainingSeconds;
          this.startCountdown();
        }
        this.cdr.detectChanges();
      },
      error: () => {
        this.cdr.detectChanges();
      }
    });
  }

  private startCountdown() {
    clearInterval(this.countdownInterval);
    this.countdownInterval = setInterval(() => {
      const remaining = this.lockedUntilMs! - Date.now();
      if (remaining <= 0) {
        this.lockedUntilMs = null;
        this.countdown = 0;
        this.failedAttempts = 0;
        this.errorMsg = '';
        clearInterval(this.countdownInterval);
        this.countdownInterval = null;
      } else {
        this.countdown = Math.ceil(remaining / 1000);
      }
      this.cdr.detectChanges();
    }, 1000);
  }

  onVarify() {
    if (this.isLocked) return;

    if (this.myLogin.invalid) {
      this.myLogin.markAllAsTouched();
      this.cdr.detectChanges();
      return;
    }

    const email = this.myLogin.getRawValue().email!;
    const password = this.myLogin.getRawValue().password!;

    this.myService.loginUser({ email, password }).subscribe({
      next: (res) => {
        const data = typeof res === 'string' ? JSON.parse(res) : res;

        // Clear everything on success
        this.clearLockState();
        this.myLogin.reset();

        this.myStorageService.setItem('authToken', data.token);
        this.myStorageService.setItem('role', data.role);
        this.myStorageService.setItem('userId', data.id);
        this.cdr.detectChanges();

        alert('Login Successful');

        const userId = this.myStorageService.getUserId();
        const role = this.myStorageService.getUserRole();

        if (role === 'ADMIN') {
          this.myRouter.navigate(['/admin']);
        } else if (role === 'DONOR') {
          if (userId !== null) {
            this.myService.getDonorByUserId(userId).subscribe({
              next: (r) => {
                localStorage.setItem('donorId', r.id);
                this.myRouter.navigate(['/donor']);
                this.cdr.detectChanges();
              },
              error: (err) => {
                if (err.status === 404) this.myRouter.navigate(['/donor-registration']);
                else alert('Something went wrong while checking donor profile');
                this.cdr.detectChanges();
              }
            });
          }
        } else if (role === 'HOSPITAL') {
          if (userId !== null) {
            this.myService.getHospitalByUserId(userId).subscribe({
              next: (r) => {
                localStorage.setItem('hospitalId', r.id);
                this.myRouter.navigate(['/hospital']);
                this.cdr.detectChanges();
              },
              error: (err) => {
                if (err.status === 404) this.myRouter.navigate(['/hospital-register']);
                else alert('Something went wrong while checking hospital profile');
                this.cdr.detectChanges();
              }
            });
          }
        } else {
          this.myRouter.navigate(['/user']);
          this.cdr.detectChanges();
        }
      },
      error: (err) => {
        const msg: string = typeof err.error === 'string' ? err.error : '';

        if (msg.startsWith('LOCKED:')) {
          const seconds = parseInt(msg.split(':')[1], 10);
          this.lockedUntilMs = Date.now() + (seconds * 1000);
          this.countdown = seconds;
          this.failedAttempts = 0;
          this.errorMsg = '';
          // Clear password only, keep email so user sees which account is locked
          this.myLogin.get('password')!.reset();
          this.startCountdown();
          this.cdr.detectChanges();
          alert('Too many failed attempts. Login locked for 3 minutes.');
        } else if (msg.startsWith('INVALID:')) {
          const remaining = parseInt(msg.split(':')[1], 10);
          this.failedAttempts = 3 - remaining;
          this.errorMsg = `Invalid email or password. ${remaining} attempt${remaining === 1 ? '' : 's'} remaining.`;
          // Clear only password so user can retype it
          this.myLogin.get('password')!.reset();
          this.cdr.detectChanges();
          alert(this.errorMsg);
        } else {
          this.errorMsg = 'Invalid email or password.';
          this.myLogin.get('password')!.reset();
          this.cdr.detectChanges();
          alert(this.errorMsg);
        }
      }
    });
  }

  ngOnDestroy() {
    clearInterval(this.countdownInterval);
  }
}
