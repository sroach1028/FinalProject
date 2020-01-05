import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  users: User[];
  username: string;

  constructor(private usersvc: UserService, private router: Router) { }

  ngOnInit() {

    this.showUsers();
  }



  showUsers() {

    this.usersvc.getAllUsers().subscribe(
      data => {
        for (let i = 0; i < data.length; i++) {

          if (data[i].username === 'admin') {
            data.splice(i, 1);
          }

        }
        this.users = data;
      },
      err => {
        console.error('Update Job error in User Compnent');
        this.router.navigateByUrl('/home');
      }
    );

    // if (this.username !== 'admin') {
    // }



  }

  setInactive(user: User) {
    user.enabled = false;
    this.usersvc.updateUser(user).subscribe(

      data => {
        console.log(data);
        // tslint:disable-next-line: prefer-for-of

      },
      err => {
        console.error('Error disabling user');
      }
    );

  }
  setActive(user: User) {
    user.enabled = true;
    this.usersvc.updateUser(user).subscribe(
      data => {

        console.log(data);
      },
      err => {
        console.error('Error enabling user');
      }
    );

  }



}
