import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from '../model/User';
import { Authenticationervice } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: User = new User();
  userForm: FormGroup;
  displayError: boolean = false;

  constructor(
    private router: Router,
    private authenticationervice: Authenticationervice,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
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
      this.authenticationervice.login(this.user).subscribe(
        (data) => {
          this.authenticationervice.extractJwt(data);
          window.location.replace('/');
        },
        (error) => {
          console.log(JSON.stringify(error));
          if (error && error.status == 401) {
            this.displayError = true;
          }
        }
      );
    }
  }
  createForm() {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
}
