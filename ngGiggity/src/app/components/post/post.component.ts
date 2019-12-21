import { SkillService } from './../../services/skill.service';
import { Address } from './../../models/address';
import { Skill } from 'src/app/models/skill';
import { JobService } from 'src/app/services/job.service';
import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  skills: Skill[] = [];

  newJob: Job = new Job();
  // newSkill: Skill = new Skill(1, 'Software Development', 'Develop your software');
  newAddy: Address = new Address();

  constructor(private jobSvc: JobService, private skillSvc: SkillService) { }
  reload() {
    this.skillSvc.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.skills = aGoodThingHappened;
      },
      (didntWork) => {
        console.error(didntWork);
      }
    );
  }
  ngOnInit() {
this.reload();
  }

  postJob(form: NgForm) {
    this.newJob = form.value;
    // this.newJob.skill = this.newSkill;
    this.newJob.active = true;
    console.log(this.newJob);
    this.jobSvc.postJob(this.newJob).subscribe(
      data => {
        console.log(data);
      },
      err => console.error('Reload error in Component')
      );
  }

}
