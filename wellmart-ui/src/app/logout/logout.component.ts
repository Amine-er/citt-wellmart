import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor( private authentocationService: AuthenticationService,
    private router: Router) { }

  ngOnInit(): void {

    this.authentocationService.logOut();
    this.router.navigateByUrl('/login');

  }

}
