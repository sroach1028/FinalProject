import { UserService } from './../../services/user.service';
import { Address } from './../../models/address';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private authSvc: AuthService, private router: Router, private usersvc: UserService) { }





  register(form: NgForm) {
    form.value.role = 'standard';
    form.value.enabled = true;

    const user = new User();
    user.firstName = form.value.firstName;
    user.lastName = form.value.lastName;
    user.username = form.value.username;
    user.email = form.value.email;
    user.password = form.value.password;
    user.phone = form.value.phone;
    user.role = form.value.role;
    user.enabled = form.value.enabled;

    const address = new Address();
    address.street = form.value.street;
    address.state = form.value.state;
    address.city = form.value.city;
    address.zip = form.value.zip;

    user.address = address;

    this.authSvc.register(user).subscribe(
      data => {
        console.log('RegisterComponent.register(): user registered.');
        this.authSvc.login(user.username, user.password).subscribe(
          next => {
            console.log('RegisterComponent.register(): user logged in, routing to /user (profile).');
            this.router.navigateByUrl('/user');
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
  }

  ngOnInit() {
  }

}
