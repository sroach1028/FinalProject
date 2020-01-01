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

background: string = null;
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
    this.randombg();
    this.reload();
  }

  randombg(){

    let images = [

      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/art.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/art2.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/baking.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/business.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/carpentry.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/construction.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/cooking.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/development.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/driving.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/homeimprovement.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/houses.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/landscaping.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/marketing.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/mechanic.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/music.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/music2.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/photography.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/photography2.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/plumbing.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/tutoring.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/videography.png')",
      "linear-gradient(rgba(255,255,255,0.4), rgba(255,255,255,0.4)), url('assets/writing.png')"

    ];
    let random= Math.floor(Math.random() * images.length) + 0;

    document.getElementById("randBackground").style.backgroundImage=images[random];
  }

}
