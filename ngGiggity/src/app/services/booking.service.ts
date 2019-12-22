import { Booking } from './../models/booking';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  // F i e l d s

  private baseUrl = environment.baseUrl;

  // M e t h o d s

  findActive(uid: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Booking[]>(this.baseUrl + 'api/booking/active/' + uid, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  findHistory(uid: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Booking[]>(this.baseUrl + 'api/booking/history/' + uid, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  createBooking(booking: Booking) {
    console.log(booking);

    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.post(this.baseUrl + 'api/booking/', booking, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('in bookingservice create booking');
        })
      );
  }

  // C o n s t r u c t o r
  constructor(private http: HttpClient, private authSvc: AuthService) { }
}
