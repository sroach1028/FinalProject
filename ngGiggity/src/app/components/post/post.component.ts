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

  newJob: Job = new Job();
  newSkill: Skill = new Skill(1, 'Software Development', 'Develop your software');
  newAddy: Address = new Address();

  constructor(private jobSvc: JobService) { }

  ngOnInit() {
  }

  postJob(form: NgForm) {
    this.newJob = form.value;
    this.newJob.skill = this.newSkill;
    console.log(this.newJob);
    this.jobSvc.postJob(this.newJob);
  }

}
