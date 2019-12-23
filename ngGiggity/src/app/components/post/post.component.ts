import { SkillService } from './../../services/skill.service';
import { Address } from './../../models/address';
import { Skill } from 'src/app/models/skill';
import { JobService } from 'src/app/services/job.service';
import { Component, OnInit } from '@angular/core';
import { Job } from 'src/app/models/job';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  skills: Skill[] = [];
  skillSel: string;
  // newJob: Job = new Job();
  newAddy: Address = new Address();

  constructor(private jobSvc: JobService, private skillSvc: SkillService, private router: Router) { }
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
    const newJob = form.value;
    // this.getSkill();
    // console.log(this.skillSel);
    console.log(newJob);
    newJob.skill = new Skill( newJob.skillId );
    delete newJob.skillId;

    newJob.active = true;
    console.log(newJob);
    this.jobSvc.postJob(newJob).subscribe(
      data => {
        console.log(data);
        this.router.navigateByUrl('/user');
      },
      err => console.error('Reload error in Component')
      );
  }

  // getSkill() {
  //   this.skills.forEach(e => {
  //     if (e.name === this.skillSel) {
  //       newJob.skill = e;
  //     }
  //   });
  // }

}
