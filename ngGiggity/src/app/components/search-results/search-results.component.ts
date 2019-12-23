import { BookingService } from './../../services/booking.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { Address } from 'src/app/models/address';
import { Job } from 'src/app/models/job';
import { Skill } from 'src/app/models/skill';
import { JobService } from 'src/app/services/job.service';
import { SkillService } from './../../services/skill.service';
import { User } from 'src/app/models/user';
import { Booking } from 'src/app/models/booking';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
  // F I E L D S
  jobs: Job[];
  jobTitle: string = null;
  title = 'Available Jobs';
  urlId: number;
  selected: Job = null;
  jobSkill: Skill = null;
  jobSkillName = null;
  jobCity = null;
  jobAddress: Address = new Address();
  booking: Booking = new Booking();
  user: User;
  updateGig: Job = null;
  skills: Skill[];
  users: User[];
  beginSearch = true;
  username = null;

  // C O N S T R U C T O R
  constructor(
    private jobSvc: JobService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private skillSvc: SkillService,
    private usersvc: UserService,
    private bookingsvc: BookingService,
  ) { }

  // M E T H O D S
  // onChange(deviceValue) {
  //   this.skills = deviceValue;
  // }
  checkSkill(job: Job, keyword: string) {
    return job.skill.name === keyword;
  }

  showUsers(user: User[]) {
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

  jobsByTitle() {
    this.jobSvc.findJobsByTitle(this.jobTitle).subscribe(
      data => {
        this.jobs = data;
      },
      err => {
        console.error('Job Title search error in Search-results component');
      }
    );
    this.beginSearch = false;
  }

  jobBySkillName() {
    this.jobSvc.findJobBySkill(this.jobSkillName).subscribe(
      aGoodThingHappened => {
        this.jobs = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
    this.beginSearch = false;
    this.jobSkillName = null;
  }

  jobByCity() {
    this.jobSvc.findJobByCity(this.jobCity).subscribe(
      aGoodThingHappened => {
        this.jobs = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
    this.beginSearch = false;
  }

  jobByRemote() {
    this.jobSvc.findJobByRemote(true).subscribe(
      aGoodThingHappened => {
        this.jobs = aGoodThingHappened;
      },
      didntWork => {
        console.error(didntWork);
      }
    );
    this.beginSearch = false;
  }

  ngOnInit() {
    this.getLoggedUser();
    console.log(this.user);
    if (this.currentRoute.snapshot.paramMap.get('skillName')) {
      this.jobSkillName = this.currentRoute.snapshot.paramMap.get('skillName');
      this.jobBySkillName();
    }
  }

  backToSearch() {
    this.users = null;
    this.jobs = null;
    this.beginSearch = true;
  }

  searchByUsername() {
    this.usersvc.getUserSByUsername(this.username).subscribe(
      data => {
        this.users = data;
      },
      err => {
        console.error('Username search error in Search-results component');
      }
    );
    this.beginSearch = false;
  }

  displaySelected(job) {
    this.selected = job;
  }
  displayMessage(j) {
    return 'hello';
  }
  getSkill(id) {
    this.skillSvc.findByJobId(id).subscribe(data => {
      this.jobSkill = data;
    });
  }

  getAddress(id) {
    this.jobSvc.findJobAddress(id).subscribe(data => {
      this.jobAddress = data;
    });
  }

  getLoggedUser() {
    // return this.usersvc.getUserByUsername();
    this.usersvc.getUserByUsername().subscribe(
      data => {
        this.user = data;
      },
      err => console.error('Reload error in Component')
    );
  }

  createBookingBuyer() {
    // this.getLoggedUser();
    console.log(this.user);
    this.booking.sellerId = this.user.id;
    this.booking.job = this.selected;
    this.bookingsvc.createBooking(this.booking).subscribe(
      data => {
        this.router.navigateByUrl('/user');
      },
      err => console.error('Reload error in Component')
    );
  }

}
