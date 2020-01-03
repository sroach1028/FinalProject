import { BookingService } from './../../services/booking.service';
import { Component, OnInit, } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { Job } from 'src/app/models/job';
import { Booking } from 'src/app/models/booking';
import { SellerReview } from 'src/app/models/seller-review';

@Component({
  selector: 'app-alt-profile',
  templateUrl: './alt-profile.component.html',
  styleUrls: ['./alt-profile.component.css']
})
export class AltProfileComponent implements OnInit {

  username: string;

  user: User;
  userJobs: Job[] = [];
  transactionHistory: Booking[] = [];
  allBoookings: Booking[] = [];
  yourGigs: Booking[];
  sellerReviews: SellerReview[] = [];
  averageSellerReview;

  constructor(private userSvc: UserService, private bookingSvc: BookingService) { }

  ngOnInit() {
    this.username = this.userSvc.profile;
    this.userSvc.findUserByUsername(this.username).subscribe(
      data => {
        this.user = data;
        this.showByBidder(data.id);
        this.showHistory(data.id);
        this.allSellerReview();
      },
      err => console.error('Reload error in User Component')
    );
    this.getUserJobs();
  }
  allSellerReview() {
    this.bookingSvc.findAllSellerReviews().subscribe(
      data => {

        // tslint:disable-next-line: prefer-for-of
        console.log('LOOOOOOOOOOOOOOOOOOOOOOK: ' + data.length);
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < data.length; i++) {

          if (data[i].booking.bid.bidder.id === this.user.id) {
            this.sellerReviews.push(data[i]);

          }



        }
        if (this.sellerReviews.length > 0) {
          this.averageSellerReview = 0;
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < this.sellerReviews.length; i++) {
            this.averageSellerReview = this.averageSellerReview + this.sellerReviews[i].rating;



          }
          console.log(this.averageSellerReview = this.averageSellerReview / this.sellerReviews.length);
        } else {
          this.averageSellerReview = 'No reviews';
        }
      },
      err => console.error('Reload error in Component')

    );



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
