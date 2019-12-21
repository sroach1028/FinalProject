import { Pipe, PipeTransform } from '@angular/core';
import { Job } from '../models/job';

@Pipe({
  name: 'jobsActive'
})
export class JobsActivePipe implements PipeTransform {

  transform(jobs: Job[]): Job[] {
    const results = [];
    jobs.forEach(e => {
      if (e.active){
        results.push(e);
      }
    });
    return results;
  }

}
