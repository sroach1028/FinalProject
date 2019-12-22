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
  allBoookings: Booking[];

  constructor(private userSvc: UserService) { }

  ngOnInit() {

  }



}
