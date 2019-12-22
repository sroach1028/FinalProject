import { Job } from 'src/app/models/job';
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
  userPastBookings: Booking[];
  userCurrentBookings: Booking[];
  allBoookings: Booking[] = null;
  user: User;
  constructor(private userSvc: UserService) { }

  ngOnInit() {
    this.getLoggedUser();
  }

  getLoggedUser(){
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.user = data;
        console.log('-------------');
      },
      err => console.error('Reload error in Component')
    );
  }

showAll() {
    this.userSvc.getBookings(this.user.id).subscribe(
      data => {
        this.allBoookings = data;
        console.log(this.allBoookings[0].job);
      },
      err => {
        console.error('Update error in Compnent');
      }
    );
}


}
