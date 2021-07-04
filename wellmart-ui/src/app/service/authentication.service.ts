import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Data } from '@angular/router';

@Injectable({
  providedIn: 'root'
})


export class AuthenticationService {

  client : any
  

  constructor(private router : Router,private httpClient:HttpClient) { }

  authenticate(username:any, password:any) :any{

    this.httpClient.get<Data>("http://localhost:8080/clients")
    .subscribe(data=>{

      this.client = data._embedded.clients

    },err=>{

      console.log(err);
    })


    for(let c of this.client[Symbol.iterator]){

    if (username === c.email && password === c.mdp) {
      sessionStorage.setItem('username', username)
      return true;
    } else {
      return false;
    }

  }

  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
    this.router.navigateByUrl("")
  }


  

}
