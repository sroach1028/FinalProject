import { BookingService } from './../../services/booking.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { UserSkill } from 'src/app/models/user-skill';
import { Job } from 'src/app/models/job';
import { Bid } from 'src/app/models/bid';
import { Booking } from 'src/app/models/booking';

@Component({
  selector: 'app-alt-profile',
  templateUrl: './alt-profile.component.html',
  styleUrls: ['./alt-profile.component.css']
})
export class AltProfileComponent implements OnInit {

  username = 'kullenk';

  user: User;
  userJobs: Job[];
  transactionHistory: Booking[] = [];
  allBoookings: Booking[] = [];
  yourGigs: Booking[];






  constructor(private userSvc: UserService, private bookingSvc: BookingService) { }

  ngOnInit() {
    this.userSvc.findUserByUsername(this.username).subscribe(
      data => {
        this.user = data;
        this.showByBidder(data.id);
        this.showHistory(data.id);
      },
      err => console.error('Reload error in User Component')
    );
    this.getUserJobs();
  }
  getUserJobs() {
    this.userSvc.findJobsByUsername(this.username).subscribe(
      data => {
        this.userJobs = data;
      },
      err => console.error('Reload error in Component')
    );
  }
  showHistory(user: number) {
    this.bookingSvc.findAll(user).subscribe(
      data => {
        this.allBoookings = data;
        this.allBoookings.forEach(b => {
          if (b.completeDate !== null) {
            this.transactionHistory.push(b);
          }
        });
      },
      err => console.error('Reload error in Component')

    );
  }
  showByBidder(user: number) {
    this.bookingSvc.findByBidder(user).subscribe(
      data => {
        console.log(data);
        this.yourGigs = data;
      },
      err => console.error('Reload error in Component')

    );
  }



}
