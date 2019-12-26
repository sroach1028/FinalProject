import { Bid } from 'src/app/models/bid';
import { Job } from 'src/app/models/job';
import { BookingService } from './../../services/booking.service';
import { User } from 'src/app/models/user';
import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/models/booking';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit {
  allBoookings: Booking[] = null;
  activeBookings: Booking[];
  transactionHistory: Booking[];
  booking: Booking = new Booking();
  user: User;
  constructor(private userSvc: UserService, private bookingSvc: BookingService) { }

  ngOnInit() {
    this.getLoggedUser();
    console.log(this.user);
    console.log(this.activeBookings);
    console.log(this.transactionHistory);
  }

  getLoggedUser() {
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.user = data;
        console.log(this.user.firstName);
        // this.active();
        // this.history();
      },
      err => console.error('Reload error in Component')
    );
  }

  showAll() {
    this.bookingSvc.findAll(this.user.id).subscribe(
      data => {
        this.allBoookings = data;
      },
      err => console.error('Reload error in Component')

    );
  }

  // active() {
  //   this.bookingSvc.findActive(this.user.id).subscribe(
  //     data => {
  //       this.activeBookings = data;
  //       console.log(this.activeBookings[0].job);
  //     },
  //     err => {
  //       console.error('Get error in active bookings');
  //     }
  //   );
  // }

  // history() {
  //   this.bookingSvc.findHistory(this.user.id).subscribe(
  //     data => {
  //       this.transactionHistory = data;
  //       console.log(this.transactionHistory[0].job);
  //     },
  //     err => {
  //       console.error('Get error in transaction histroy');
  //     }
  //   );
  // }

  createBooking(job: Job, bid: Bid) {
    console.log(this.booking);
    this.booking.job = job;
    this.booking.bid = bid;
    this.bookingSvc.createBooking(this.booking).subscribe(
      data => {
      },
      err => console.error('Create error in search-result-Component createBid')
    );
  }

}
