import { Booking } from './../models/booking';
import { Job } from 'src/app/models/job';
import { DatePipe } from "@angular/common";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { User } from '../models/user';
import { AuthService } from "./auth.service";
import { Skill } from '../models/skill';
import { UserSkill } from '../models/user-skill';

@Injectable({
  providedIn: "root"
})
export class UserService {
  private baseUrl = environment.baseUrl;
  profile;

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private authSvc: AuthService
  ) { }

  index() {
    if (!this.authSvc.checkLogin()) {
      return null;
    } else {
      const httpOptions = {
        headers: new HttpHeaders({
          "Content-Type": "application/json",
          Authorization: "Basic " + this.authSvc.getCredentials()
        })
      };
      //F I X  U R L
      return this.http
        .get<User>(this.baseUrl + 'api/users/getUser', httpOptions)
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError("KABOOM");
          })
        );
    }
  }
  profileUsername(profile: string) {
    console.log('LOOOOOKK HERERE: ' + profile)
this.profile = profile;
  }
  getUserByUsername() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.get<User>(this.baseUrl + 'users/username/', httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );

  }
  findUserByUsername(username: string) {

    return this.http.get<User>(this.baseUrl + 'profile/username/' + username)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );

  }

  getUserSByUsername(term: string) {

    return this.http.get<User[]>(this.baseUrl + 'users/username/' + term)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );

  }

  getJobsByUsername() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Job[]>(this.baseUrl + 'api/jobs/username/', httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }
  findJobsByUsername(username: string) {

    return this.http.get<Job[]>(this.baseUrl + 'jobs/username/' + username)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  getSkillsByUsername() {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.get<UserSkill[]>(this.baseUrl + 'api/userSkill/username/', httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  getBookings(id) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Booking[]>(this.baseUrl + 'api/booking/user/' + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  updateUser(user: User) {
    const httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.put<User>(this.baseUrl + 'api/users/' + user.id, user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }
}
