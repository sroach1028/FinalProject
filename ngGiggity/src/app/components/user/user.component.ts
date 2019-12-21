import { JobService } from 'src/app/services/job.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { UserSkill } from 'src/app/models/user-skill';
import { UserSkillService } from 'src/app/services/user-skill.service';
import { Job } from 'src/app/models/job';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  title = 'Profile';
  userSelected = null;
  userSkills: UserSkill[] = [];
  userJobs: Job[] = [];
  skillName: string;
  // tslint:disable-next-line: max-line-length

  constructor(private userSvc: UserService, private userSkillSvc: UserSkillService, private jobSvc: JobService) { }


  ngOnInit() {
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.userSelected = data;
        console.log('-------------');
      },
      err => console.error('Reload error in Component')
      );
    this.userSvc.getSkillsByUsername().subscribe(
        data => {
          this.userSkills = data;
        },
        err => console.error('Reload error in Component')
      );
    this.userSvc.getJobsByUsername().subscribe(
        data => {
          this.userJobs = data;
        },
        err => console.error('Reload error in Component')
        );
      }


  getSkillName(id) {
    this.userSkillSvc.findSkillName(id).subscribe(
      data => {
        console.log('--------------------------');
        console.log(data);
        this.skillName = data.name;
      }
    );
  }



}
