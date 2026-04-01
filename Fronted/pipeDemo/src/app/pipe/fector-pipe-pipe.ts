import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'fectorPipe',
  standalone: false,
})
export class FectorPipePipe implements PipeTransform {
  transform(value: number, ...args: number[]): number {

    let a:number = 1;

    for(let i = 1; i <= value ; i++ ){
      a = i*a;
    }

    return a;
  }
}
