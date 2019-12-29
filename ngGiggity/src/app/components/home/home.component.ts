import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';
import { Component, Input, ViewChild, NgZone, OnInit } from '@angular/core';
import { MapsAPILoader, AgmMap } from '@agm/core';
import { GoogleMapsAPIWrapper } from '@agm/core';
import { Skill } from 'src/app/models/skill';

// END MAP STUFF

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
      this.skills = aGoodThingHappened;
      this.totalskills = this.skills.length;
    },
    (didntWork) => {
      console.error(didntWork);
    }
  );
}

  constructor(private skillSvc: SkillService, private jobSvc: JobService){}

  ngOnInit() {
    this.reload();
  }

}
