import { DatePipe } from "@angular/common";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { environment } from "src/environments/environment";
import { User } from '../models/user';
import { AuthService } from "./auth.service";

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
  getUserByUsername() {
    return this.http.get<User>(this.baseUrl + 'users/username/')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );

  }

  constructor(
    private http: HttpClient,
    private datePipe: DatePipe,
    private authSvc: AuthService
  ) { }
}
