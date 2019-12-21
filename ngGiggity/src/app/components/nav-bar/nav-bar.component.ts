import { SearchResultsComponent } from './../search-results/search-results.component';
import { HomeComponent } from './../home/home.component';
import { NgForm } from '@angular/forms';
import { UserService } from './../../services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private authSvc: AuthService, private usersvc: UserService, private router: Router) { }

  searchDisplay = 'user';

  searchBy(event: any) {
    this.searchDisplay = event.target.value;
    console.log(event);
  }

  isLoggedIn() {
    return this.authSvc.checkLogin();
  }
  logOut() {
    this.authSvc.logout();
  }

  ngOnInit() {

  }

}
