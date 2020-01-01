import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from 'src/app/models/user';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  constructor(private authSvc: AuthService,  private router: Router) {
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


  ngOnInit() {
  }

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

  ngOnDestroy() {
    // avoid memory leaks here by cleaning up after ourselves. If we
    // don't then we will continue to run our initialiseInvites()
    // method on every navigationEnd event.
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

}
