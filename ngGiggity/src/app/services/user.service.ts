import { Job } from "./../models/job";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { DatePipe } from "@angular/common";
import { AuthService } from "./auth.service";
import { environment } from "src/environments/environment";
import { catchError } from "rxjs/operators";
import { throwError } from "rxjs";
import { User } from '../models/user';

@Injectable({
  providedIn: "root"
})
export class UserService {
  private baseUrl = environment.baseUrl;

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

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private authSvc: AuthService
  ) {}
}
