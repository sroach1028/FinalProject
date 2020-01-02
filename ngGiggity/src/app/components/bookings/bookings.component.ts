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
  allBoookings: Booking[] = [];
  activeBookings: Booking[] = [];
  yourGigs: Booking[];
  transactionHistory: Booking[] = [];
  booking: Booking = new Booking();
  user: User;
  constructor(private userSvc: UserService, private bookingSvc: BookingService) { }

  ngOnInit() {
    this.randombg();
    this.getLoggedUser();
  }

  getLoggedUser() {
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.user = data;
        this.showAll();
        this.showByBidder();
      },
      err => console.error('Reload error in Component')
    );
  }

  showAll() {
    this.bookingSvc.findAll(this.user.id).subscribe(
      data => {
        this.allBoookings = data;
        this.allBoookings.forEach(b => {
        if (b.completeDate !== null){
          this.transactionHistory.push(b);
        } else {
          this.activeBookings.push(b);
        }
        });
      },
      err => console.error('Reload error in Component')

    );
  }

  showByBidder() {
    this.bookingSvc.findByBidder(this.user.id).subscribe(
      data => {
        this.yourGigs = data;
      },
      err => console.error('Reload error in Component')

    );
  }

  updateBooking(booking: Booking) {
    this.bookingSvc.updateBooking(booking).subscribe(
      data => {
        this.ngOnInit();
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
    this.booking.job = job;
    this.booking.bid = bid;
    this.bookingSvc.createBooking(this.booking).subscribe(
      data => {
      },
      err => console.error('Create error in search-result-Component createBid')
    );
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
