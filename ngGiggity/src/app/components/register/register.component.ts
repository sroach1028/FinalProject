import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authSvc: AuthService) { }

  register(form: NgForm) {
    form.value.role = 'standard';
    form.value.enabled = true;
    const user = form.value;

    this.authSvc.register(user).subscribe(
      data => {
        console.log('RegisterComponent.register(): user registered.');
        this.authSvc.login(user.username, user.password).subscribe(
          next => {
            console.log('RegisterComponent.register(): user logged in, routing to /todo.');
            // this.router.navigateByUrl('/todo');
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      },
      err => {
        console.error('RegisterComponent.register(): error registering.');
        console.error(err);
      }
    );

    console.log(form);

  }

  ngOnInit() {
  }

}
