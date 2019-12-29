import { NgForm } from '@angular/forms';
import { SkillService } from './../../services/skill.service';
import { BookingService } from './../../services/booking.service';
import { ActiveBidPipe } from './../../pipes/active-bid.pipe';
import { JobService } from 'src/app/services/job.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { UserSkill } from 'src/app/models/user-skill';
import { UserSkillService } from 'src/app/services/user-skill.service';
import { Job } from 'src/app/models/job';
import { Router } from '@angular/router';
import { Skill } from 'src/app/models/skill';
import { User } from 'src/app/models/user';
import { BidService } from 'src/app/services/bid.service';
import { Bid } from 'src/app/models/bid';
import { Booking } from 'src/app/models/booking';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  title = 'Profile';
  userSelected: User = null;
  userSkills: UserSkill[] = [];
  updateGig: Job = null;
  selected: Job = null;
  userJobs: Job[];
  skillName: string;
  skills: Skill[];
  user: User;
  bids: Bid[];
  sellersBids: Bid[];
  selectedBid: Bid;
  booking: Booking = new Booking();
  selectSkills = null;
  userSkillDescription: string;
  updateProfile = false;

  // tslint:disable-next-line: max-line-length

  constructor(
    private bidSvc: BidService,
    private userSvc: UserService,
    private userSkillSvc: UserSkillService,
    private jobSvc: JobService,
    private router: Router,
    private activeBid: ActiveBidPipe,
    private bookingSvc: BookingService,
    private skillsvc: SkillService
  ) {
    // //reloads current URL with new search term
    // this.navigationSubscription = this.router.events.subscribe(
    //   (e: any) => {
    //     if (e instanceof NavigationEnd) {
    //       console.log("Inside instanceOf NavigationEnd");
    //       this.keyword = this.currentRoute.snapshot.paramMap.get('skillName');
    //       if (this.keyword) {
    //         this.jobSvc.findJobBySkill(this.keyword).subscribe(
    //           data => {
    //             this.selected = data.find(this.checkSkill)
    //           },
    //           err => {
    //           }
    //         );
    //       }
    //     }
    //   }
    // );
  }

  chooseSkills() {
    this.selectSkills = true;
  }

  remove(job: Job) {
    job.active = false;
    this.update(job);
  }

  displaySelected(job) {
    this.selected = job;
  }

  setUpdateGig(job: Job) {
    this.selected = job;
    this.updateGig = Object.assign({}, job);
  }

  update(job: Job) {
    this.jobSvc.update(job.id, job).subscribe(
      data => {
        this.ngOnInit();

        this.updateGig = null;
      },
      err => {
        console.error('Update Job error in User Compnent');
      }
    );
  }
  ngOnInit() {
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.userSelected = data;
      },
      err => console.error('Reload error in User Component')
    );
    this.getUserJobs();
    this.showBidsByBidder();
    this.getAllSkills();
    this.getUserSkills();
  }

  getUserJobs() {
    this.userSvc.getJobsByUsername().subscribe(
      data => {
        this.userJobs = data;
      },
      err => console.error('Reload error in Component')
    );
  }

  getUserSkills() {
    this.userSvc.getSkillsByUsername().subscribe(
      data => {
        this.userSkills = data;
      },
      err => console.error('Reload error in Component')
    );
  }

  getSkillName(id) {
    this.userSkillSvc.findSkillName(id).subscribe(data => {
      this.skillName = data.name;
    });
  }

  showBids(job: Job) {
    this.selected = job;
    this.bids = job.jobBids;
  }

  viewAllGigs() {
    this.bids = null;
    this.selected = null;
  }

  showBidsByBidder() {
    this.bidSvc.getBidsByBidder().subscribe(
      data => {
        this.sellersBids = data;
      },
      err => {
        console.error('Get error in search-result-Component showBidsByBidder');
      }
    );
  }

  acceptBid(bid: Bid) {
    this.selectedBid = bid;
    this.booking.bid = bid;
    this.booking.job = this.selected;
    this.bookingSvc.createBooking(this.booking).subscribe(
      data => {
        this.router.navigateByUrl('/bookings');
      },
      err => console.error('Create error in search-result-Component createBid')
    );

    for (let rejectBid of this.bids) {
      rejectBid.accepted = false;
      rejectBid.rejected = true;
      this.bidSvc.updateBid(rejectBid).subscribe(
        data => {
          rejectBid = data;
        },
        err => {
          console.error('Update error in search-result-Component acceptBid');
        }
      );
    }

    this.selectedBid.accepted = true;
    this.selectedBid.rejected = false;
    this.bidSvc.updateBid(this.selectedBid).subscribe(
      data => {
        this.selectedBid = data;
      },
      err => {
        console.error('Update error in search-result-Component acceptBid');
      }
    );
  }

  rejectBid(rejectedBid: Bid) {
    rejectedBid.accepted = false;
    this.bidSvc.updateBid(rejectedBid).subscribe(
      data => {},
      err => console.error('Create error in search-result-Component createBid')
    );
  }

  getAllSkills() {
    this.skillsvc.index().subscribe(
      data => {
        this.skills = data;
      },
      err => {
        console.log('Error getting SKills in User component');
      }
    );
  }

  addUserSkill(skill: Skill, descForm: NgForm) {
    const userSkill = new UserSkill();
    userSkill.skill = skill;

    const desc: string = descForm.value.userSkillDescription;

    userSkill.description = desc;

    this.userSkillSvc.createUserSkill(userSkill).subscribe(
      data => {
      },
      err => {
        console.log('Error getting SKills in User component');
      }
    );
    this.userSkills.push(userSkill);
    this.selectSkills = null;
    this.ngOnInit();
    }

  userToUpdate() {
    this.updateProfile = true;
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.userSelected = data;
      },
      err => {
        console.error('userToUpdate Error: User Component');
      }
    );
  }

  updateUser() {
    this.userSvc.updateUser(this.userSelected).subscribe(
      data => {
        this.userSelected = data;
      },
      err => {
        console.error('Update User Error: User Component');
      }
    );
    this.updateProfile = false;
  }

}
