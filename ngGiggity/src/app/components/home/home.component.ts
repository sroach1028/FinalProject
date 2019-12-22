import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';
import { Component, OnInit } from '@angular/core';
import { Skill } from 'src/app/models/skill';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


skills: Skill[] = [];
totalskills: number;
reload() {
  this.skillSvc.index().subscribe(
    (aGoodThingHappened) => {
      // console.log(aGoodThingHappened);
      this.skills = aGoodThingHappened;
      this.totalskills = this.skills.length;
    },
    (didntWork) => {
      console.error(didntWork);
    }
  );
}

  constructor(private skillSvc: SkillService, private jobSvc: JobService) { }

  ngOnInit() {
    this.reload();
  }

}
