import { Skill } from './../../models/skill';
import { SkillService } from './../../services/skill.service';
import { UserSkill } from './../../models/user-skill';
import { UserService } from './../../services/user.service';
import { Address } from './../../models/address';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy  {

  constructor(private authSvc: AuthService, private router: Router, private usersvc: UserService, private skillsvc: SkillService) {
    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      // If it is a NavigationEnd event re-initalise the component
      if (e instanceof NavigationEnd) {
        this.error = false;
        this.ngOnInit();
      }
    });
   }
  error = false;
  navigationSubscription;

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
    user.bio = form.value.bio;

    const address = new Address();
    address.street = form.value.street;
    address.state = form.value.state;
    address.city = form.value.city;
    address.zip = form.value.zip;

    user.address = address;
    this.error = false;
    this.authSvc.register(user).subscribe(
      data => {
        this.authSvc.login(user.username, user.password).subscribe(
          next => {
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
        this.error = true;
      }
    );
  }

  ngOnInit() {}

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

}
