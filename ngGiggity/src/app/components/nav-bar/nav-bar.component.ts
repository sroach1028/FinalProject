import { SearchResultsComponent } from './../search-results/search-results.component';
import { HomeComponent } from './../home/home.component';
import { NgForm } from '@angular/forms';
import { UserService } from './../../services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private authSvc: AuthService, private usersvc: UserService, private router: Router) { }

  searchDisplay = 'user';
  userSelected: User = null;
  userFullName: string = null;

  searchBy(event: any) {
    this.searchDisplay = event.target.value;
  }

  isLoggedIn() {
    if (this.authSvc.checkLogin() && this.userFullName === null) {
      this.getUser();
    }
    return this.authSvc.checkLogin();
  }

  logOut() {
    this.authSvc.logout();
    this.userFullName = null;
  }

  ngOnInit() {

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

}
