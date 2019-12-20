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


  constructor(private skillSvc: SkillService) { }

  ngOnInit() {
    this.reload();
  }

}
