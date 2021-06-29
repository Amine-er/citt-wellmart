import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Authenticationervice } from 'app/service/authentication.service';
import { User } from 'app/shared/models/user';
import { JwtHelperService } from "@auth0/angular-jwt";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authenticationervice:Authenticationervice,private fb: FormBuilder , private router: Router) { }
   user: User = new User();
   userForm: FormGroup;
   displayError: boolean = false;
  ngOnInit(): void {
    if (this.authenticationervice.isAuthenticated()) {
      this.authenticationervice.getPermissions();
       this.router.navigate(['/dashboard']);
    }
    this.createForm();
   
  }

  login() {
    let username = this.userForm.controls['username'].value;
    let password = this.userForm.controls['password'].value;
    this.displayError = false;
    const helper = new JwtHelperService();

    if (username && password) {
         this.user.username = username;
        this.user.password = password;
        this.authenticationervice.login(this.user).subscribe(data => {
          this.authenticationervice.extractJwt(data);
            this.router.navigate(['/dashboard']);
    }, error => {
        console.log(JSON.stringify(error))
        if (error && error.status == 401) {
          this.displayError = true;
        }
    })
    }
  }
  createForm() {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
}
