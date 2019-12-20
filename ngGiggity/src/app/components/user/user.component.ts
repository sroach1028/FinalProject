import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  userSelected = null;

  constructor(private userSvc: UserService) { }


  ngOnInit() {
    this.userSvc.getUserByUsername().subscribe(
      data => {
        this.userSelected = data;
  },
  err => console.error('Reload error in Component')
  );
  }



}
