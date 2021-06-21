import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "app/shared/models/user";
import { Observable } from "rxjs";
import { MainConfigService } from "./main-config.service";

@Injectable({
    providedIn: 'root'
  })
  export class Authenticationervice {
  
    
    constructor(private httpClient: HttpClient,private mainConfig:MainConfigService ) {
     
    }

    public login(user): Observable<User> {
        let body = new HttpParams();
        body = body.set('username', user.username);
        body = body.set('password', user.password);
       return this.httpClient.post<User>(this.mainConfig.getLoginUrl(),body);
     }
  
  }
  