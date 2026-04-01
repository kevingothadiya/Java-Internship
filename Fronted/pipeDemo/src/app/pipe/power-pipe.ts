import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'power',
  standalone: false,
})
export class PowerPipe implements PipeTransform {
  transform(myNumber: number, ...args: number[]): number {
    return Math.pow(myNumber ,args[0]);
  }
}
