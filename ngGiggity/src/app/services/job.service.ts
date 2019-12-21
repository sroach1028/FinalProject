import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Job } from '../models/job';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { Address } from '../models/address';
import { Skill } from '../models/skill';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  //F I E L D S
  private url = environment.baseUrl + 'jobs';

  //C O N S T R U C T O R
  constructor(private http: HttpClient, private authSvc: AuthService) { }

  index() {
    return this.http.get<Job[]>(this.url)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  findJobAddress(jid) {
    return this.http.get<Address>(this.url + '/address/' + jid)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  findJobBySkill(skillName: string) {
    return this.http.get<Job[]>(this.url + '/skill/' + skillName)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('KABOOM');
        })
      );
  }

  update(id: number, updateJob: Job) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + this.authSvc.getCredentials()
      })
    };
    return this.http
      .put<Event>(this.url + '/' + id, updateJob, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('Service Error: Update Method');
        })
      );
  }
}
