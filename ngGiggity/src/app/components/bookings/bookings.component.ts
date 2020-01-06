import { NgForm } from '@angular/forms';
import { SellerReview } from './../../models/seller-review';
import { Bid } from 'src/app/models/bid';
import { Job } from 'src/app/models/job';
import { BookingService } from './../../services/booking.service';
import { User } from 'src/app/models/user';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Booking } from 'src/app/models/booking';
import { UserService } from 'src/app/services/user.service';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-bookings',
  templateUrl: './bookings.component.html',
  styleUrls: ['./bookings.component.css']
})
export class BookingsComponent implements OnInit, OnDestroy {
  allBoookings: Booking[] = [];
  activeBookings: Booking[] = [];
  yourGigs: Booking[];
  transactionHistory: Booking[] = [];
  booking: Booking = new Booking();
  user: User;
  review: SellerReview = new SellerReview();
  sellerReviews: SellerReview[] = [];
  bookingReviews = [];
  // tslint:disable-next-line: max-line-length
  constructor(private userSvc: UserService, private bookingSvc: BookingService, private router: Router) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.ngOnInit();
      }
    });
  }
  navigationSubscription;

  ngOnInit() {
    this.randombg();
    this.getLoggedUser();
    // this.allSellerReview();
  }
  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  getLoggedUser() {
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.user = data;
        this.showAll();
        // this.showByBidder();
      },
      err => console.error('Reload error in Component')
    );
  }
  sellerReview(form: NgForm) {
    let review = new SellerReview();

    review = form.value;

    review.booking = form.value.booking;

    console.log(this.review);


    this.bookingSvc.createSellerReview(review, review.booking.id).subscribe(
      data => {
        console.log(data);
      },
      err => console.error('review didnt work')

    );






  }


  allSellerReview() {
    this.bookingSvc.findAllSellerReviews().subscribe(
      data => {
        console.log(data);
        this.sellerReviews = data;
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.sellerReviews.length; i++) {
          // tslint:disable-next-line: prefer-for-of
          for (let j = 0; j < this.allBoookings.length; j++) {
            if (this.sellerReviews[i].booking.id === this.allBoookings[j].id) {

            }
          }
        }
      },
      err => console.error('Reload error in Component')

    );
  }

  showAll() {
    this.bookingSvc.findAll(this.user.id).subscribe(
      data => {
        this.allBoookings = data;
        this.allBoookings.forEach(b => {
          if (b.completeDate !== null) {
            this.transactionHistory.push(b);
          } else {
            this.activeBookings.push(b);
          }
        });
      },
      err => console.error('Reload error in Component')

    );

    // showByBidder //
    this.bookingSvc.findByBidder(this.user.id).subscribe(
      data => {
        this.yourGigs = data;
      },
      err => console.error('Reload error in Component')

    );
  }

  // showByBidder() {
  //   this.bookingSvc.findByBidder(this.user.id).subscribe(
  //     data => {
  //       this.yourGigs = data;
  //     },
  //     err => console.error('Reload error in Component')

  //   );
  // }

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

  randombg() {
    const images = [

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
    const random = Math.floor(Math.random() * images.length) + 0;

    document.getElementById("randBackground").style.backgroundImage = images[random];
  }

}
