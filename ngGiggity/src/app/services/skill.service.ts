import { Skill } from './../models/skill';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  private baseUrl = environment.baseUrl;



  index() {

    return this.http.get<Skill[]>(this.baseUrl + 'skills' + '?sorted=true')
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );

  }

  findByJobId(id){
    return this.http.get<Skill>(this.baseUrl + 'jobskill/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('KABOOM');
      })
    );

  }













  constructor(private http: HttpClient, private datePipe: DatePipe, private authSvc: AuthService) { }
}
