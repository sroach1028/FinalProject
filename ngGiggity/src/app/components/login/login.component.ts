import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error = false;
  login(form: NgForm) {
    this.error = false;
    const user: User = form.value;
    this.authSvc.login(user.username, user.password).subscribe(
      goodStuff => {
        this.router.navigateByUrl('/user');

      },

      badStuff => {
        console.error(badStuff);
        this.error = true;


      }
    );

  }
  constructor(private authSvc: AuthService,  private router: Router) { }

  ngOnInit() {
  }

}
