import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job';
import { JobService } from 'src/app/services/job.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  jobs: Job[];
  title = 'Available Jobs'
  urlId: number;
  selected: Job = null;
  constructor(private jobSvc: JobService, private currentRoute: ActivatedRoute, private router: Router,
    ) { }

  ngOnInit() {
    this.jobSvc.index().subscribe(
      data => {
        this.jobs = data;
        if (this.currentRoute.snapshot.paramMap.get('id')) {
          this.urlId = +this.currentRoute.snapshot.paramMap.get('id');
          this.jobs.forEach(e => {
            if (e.id === this.urlId) {
              this.selected = e;
            }

          });
          if (this.selected == null) {
            this.router.navigateByUrl('**');
          }
        }
  },
  err => console.error('Reload error in Component')
  );
}

displaySelected(job) {
  this.selected = job;
}
}
