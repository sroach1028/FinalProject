import { Skill } from 'src/app/models/skill';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserSkillService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient, private authSvc: AuthService
    ) { }

  findSkillName(id){
    const httpOptions = {
      headers: new HttpHeaders({
        // "Content-Type": "application/json",
        Authorization: "Basic " + this.authSvc.getCredentials()
      })
    };
    return this.http.get<Skill>(this.baseUrl + 'api/userSkill/skillName/' + id, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log(err);
          console.log('SKILLSERVICE');
          return throwError('KABOOM');
        })
      );

  }
}
