import { SearchResultsComponent } from './../search-results/search-results.component';
import { HomeComponent } from './../home/home.component';
import { NgForm } from '@angular/forms';
import { UserService } from './../../services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit, OnDestroy {

  constructor(private authSvc: AuthService, private usersvc: UserService, private router: Router) {this.navigationSubscription = this.router.events.subscribe((e: any) => {
    // If it is a NavigationEnd event re-initalise the component
    if (e instanceof NavigationEnd) {
      this.ngOnInit();
    }
  }); }

  searchDisplay = 'user';
  userSelected: User = null;
  userFullName: string = null;
  loggedIn = false;
  navigationSubscription;


  searchBy(event: any) {
    this.searchDisplay = event.target.value;
  }

  isLoggedIn() {
    return this.authSvc.checkLogin();
  }

  logOut() {
    this.userFullName = null;
    this.loggedIn = false;
    this.authSvc.logout();
  }

  ngOnInit() {
    if(this.authSvc.checkLogin()) {
      this.loggedIn = true;
      this.getUser();
    }
  }

  getUser() {
    this.usersvc.getUserByUsername().subscribe(
      data => {
        this.userSelected = data;
        this.userFullName = this.userSelected.firstName + ' ' + this.userSelected.lastName;
      },
      err => console.error('Get User Error in Nav-Bar Component.ts')
    );

    this.userSelected = null;
  }


  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

}
