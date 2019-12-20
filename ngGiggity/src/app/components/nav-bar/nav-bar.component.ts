import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private authSvc: AuthService) { }

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
