import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Authenticationervice } from 'app/service/authentication.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './merchant.component.html',
  styleUrls: ['./merchant.component.css']
})
export class MerchantComponent implements OnInit {
panelOpenState = false;
  constructor(private authenticationervice: Authenticationervice, private router: Router, route: ActivatedRoute) {
    route.params.subscribe(val => {
     if (this.authenticationervice.isAuthenticated()) {
            this.authenticationervice.getPermissions();
        } else {
            this.authenticationervice.logout();
             this.router.navigate(['/login']);
        }
  });
  }

  ngOnInit() {
    
  }

}
