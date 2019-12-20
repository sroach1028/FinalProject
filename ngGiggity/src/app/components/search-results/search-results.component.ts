import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Job } from 'src/app/models/job';
import { Skill } from 'src/app/models/skill';
import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';


@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  //F I E L D S
  jobs: Job[];
  title = 'Available Jobs'
  urlId: number;
  selected: Job = null;
  jobSkill: Skill = null;
  jobAddress: Address = new Address();

  // C O N S T R U C T O R
  constructor(private jobSvc: JobService, private currentRoute: ActivatedRoute, private router: Router,
              private skillSvc: SkillService) { }

//M E T H O D S

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
            this.getSkill(e.id);
            e.skill = this.jobSkill;
            this.getAddress(e.id);
            if (this.jobAddress != null) {
            e.address = this.jobAddress;
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
displayMessage(j) {
  return 'hello';
}
getSkill(id) {
this.skillSvc.findByJobId(id).subscribe(
  data => {
    this.jobSkill = data;
  }
);
}

getAddress(id){
  this.jobSvc.findJobAddress(id).subscribe(
    data => {
      this.jobAddress = data;
    }
  );
}


}
