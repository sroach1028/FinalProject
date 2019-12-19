import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  login(form: NgForm) {
    const user: User = form.value;
    this.authSvc.login(user.username, user.password).subscribe(
      goodStuff => {
        // this.userSvc.index();
        console.log('succsessful login');
      },

      badStuff => {
        console.error(badStuff);
        console.error('BADDDDD');


      }
    );

  }
  constructor(private authSvc: AuthService) { }

  ngOnInit() {
  }

}
