import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { Authenticationervice } from '../services/authentication.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
})
export class NavBarComponent implements OnInit {
  currentUser: string = null;
  constructor(
    public loginService: AuthenticationService,
    private authenticationervice: Authenticationervice
  ) {}

  ngOnInit(): void {
    this.currentUser = this.authenticationervice.getUserName();
  }

  logout() {
    this.authenticationervice.logout();
  }
}
