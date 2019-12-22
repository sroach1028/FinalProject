import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Job } from 'src/app/models/job';
import { Skill } from 'src/app/models/skill';
import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  // F I E L D S
  jobs: Job[];
  title = 'Available Jobs';
  urlId: number;
  selected: Job = null;
  jobSkill: Skill = null;
  jobAddress: Address = new Address();
  updateGig: Job = null;
  skills: Skill[];
  users: User[];
  beginSearch = true;
  username = null;
  user: User;

  // C O N S T R U C T O R
  constructor(private jobSvc: JobService, private currentRoute: ActivatedRoute, private router: Router,
              private skillSvc: SkillService, private usersvc: UserService) {}



// M E T H O D S
// onChange(deviceValue) {
//   this.skills = deviceValue;
// }
 checkSkill(job: Job, keyword: string) {
  return job.skill.name === keyword;
  }

  showUsers(user: User []){
    this.users = user;
  }

getSkills() {
  this.skillSvc.index().subscribe(
    data => {
      this.skills = data;
    },
    err => {
      console.error('Update error in Compnent');
    }
  );
}



jobBySkillName(skillName: string) {
  this.jobSvc.findJobBySkill(skillName).subscribe(
    (aGoodThingHappened) => {
      console.log(aGoodThingHappened);
      this.jobs = null;
      this.jobs = aGoodThingHappened;
    },
    (didntWork) => {
      console.error(didntWork);
    }
  );
}

ngOnInit() {
}

searchByUsername() {
  console.log(this.username);
  this.usersvc.getUserByUsername(this.username).subscribe(
    data => {
      this.user = data;
      console.log(this.user);
    },
    err => {
      console.error('Username search error in Search-results component');
    }
  );
  this.beginSearch = false;
}

// this.getSkills();
//   this.jobBySkillName(this.currentRoute.snapshot.paramMap.get('skillName'));
//   if (!this.currentRoute.snapshot.paramMap.get('skillName')) {
//     this.jobSvc.index().subscribe(
//       data => {
//         this.jobs = data;
//         if (this.currentRoute.snapshot.paramMap.get('id')) {
//           this.urlId = +this.currentRoute.snapshot.paramMap.get('id');
//           this.jobs.forEach(e => {
//             if (e.id === this.urlId) {
//               this.selected = e;
//             }
//             this.getSkill(e.id);
//             e.skill = this.jobSkill;
//             this.getAddress(e.id);
//             if (this.jobAddress != null) {
//               e.address = this.jobAddress;
//             }
//           });
//           if (this.selected == null) {
//             this.router.navigateByUrl('**');
//           }
//         }
//       },
//       err => console.error('Reload error in Component')
//     );
//   }

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

getAddress(id) {
  this.jobSvc.findJobAddress(id).subscribe(
    data => {
      this.jobAddress = data;
    }
  );
}


}
