import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  users: User[];
  username: string;
  allUsers = false;

  constructor(private usersvc: UserService) { }

  ngOnInit() {
  }



  showUsers() {
    this.usersvc.getAllUsers().subscribe(
      data => {
        this.users = data;
      },
      err => {
        console.error('Update Job error in User Compnent');
      }
    );
    this.allUsers = true;



  }

  setInactive(user: User) {
    user.enabled = false;
    this.usersvc.updateUser(user).subscribe(

      data => {
        console.log(data);
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
