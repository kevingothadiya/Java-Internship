import { ChangeDetectorRef, Component, inject, OnInit } from '@angular/core';
import { ServiceProvider } from '../../service/service-provider';

@Component({
  selector: 'app-blood-stock-details',
  standalone: false,
  templateUrl: './blood-stock-details.html',
  styleUrl: './blood-stock-details.css',
})
export class BloodStockDetails implements OnInit {

  myService = inject(ServiceProvider);
  cdr = inject(ChangeDetectorRef);

  bloodStocks: any[] = [];
  loading = false;

  ngOnInit(): void { this.loadBloodStock(); }

  loadBloodStock() {
    this.loading = true;
    this.myService.getBloodStock().subscribe({
      next: (resp) => {
        this.bloodStocks = resp;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }

  getStockLevel(units: number): string {
    if (units === 0) return 'empty';
    if (units < 5) return 'critical';
    if (units < 15) return 'low';
    return 'normal';
  }

  getStockBadgeClass(units: number): string {
    const level = this.getStockLevel(units);
    return {
      empty: 'bg-secondary',
      critical: 'bg-danger',
      low: 'bg-warning text-dark',
      normal: 'bg-success',
    }[level] || 'bg-secondary';
  }

  getStockLabel(units: number): string {
    return {
      empty: 'Empty',
      critical: 'Critical',
      low: 'Low',
      normal: 'Available',
    }[this.getStockLevel(units)] || '';
  }

  getTotalUnits(): number {
    return this.bloodStocks.reduce((sum, s) => sum + (s.unitsAvailable || 0), 0);
  }

  getCriticalCount(): number {
    return this.bloodStocks.filter(s => this.getStockLevel(s.unitsAvailable) === 'critical' || this.getStockLevel(s.unitsAvailable) === 'empty').length;
  }

  getNormalCount(): number {
    return this.bloodStocks.filter(s => this.getStockLevel(s.unitsAvailable) === 'normal').length;
  }
}
