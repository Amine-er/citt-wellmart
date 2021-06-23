import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Token } from "app/shared/models/token";
import { User } from "app/shared/models/user";
import { Observable } from "rxjs";
import { MainConfigService } from "./main-config.service";
import { JwtHelperService } from "@auth0/angular-jwt";
import { NgxPermissionsService } from "ngx-permissions";

@Injectable({
    providedIn: 'root'
  })
  export class Authenticationervice {
  
    constructor(private httpClient: HttpClient,private mainConfig:MainConfigService ,private permissionsService: NgxPermissionsService,) {
     
    }

  public isAuthenticated() :boolean{
    return sessionStorage.getItem("jwt") != null && sessionStorage.getItem("jwt") != "" && sessionStorage.getItem("jwt") != undefined;
  }
  public extractJwt(token: Token) {
    sessionStorage.setItem("jwt", token.jwt)
    this.getPermissions();
  }

  public logout() {

    this.permissionsService.loadPermissions([]);
        sessionStorage.removeItem("jwt")
  }
  public getUserName(): string{
        const helper = new JwtHelperService();
    const token = sessionStorage.getItem("jwt")
    let username = "";
    if (token) {
          const decodedToken = helper.decodeToken(token);
      username = decodedToken.user;
    }
    return username;
  }
  public getPermissions(): string[]{
    
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem("jwt")
    if (token) {
      const decodedToken = helper.decodeToken(token);
      let authorities = [];
      if (decodedToken.role[0]) {
        authorities = decodedToken.role[0].authorities;
        let permssions = [];
        authorities.forEach(function (item) {
          permssions.push(item.permission);
        })
        this.permissionsService.loadPermissions(permssions);
            return permssions;
      }
    }
    else {
      return [];
    }
  

  }
  
    public login(user): Observable<Token> {
        let body = new HttpParams();
        body = body.set('username', user.username);
        body = body.set('password', user.password);
       return this.httpClient.post<Token>(this.mainConfig.getLoginUrl(),body);
     }
  
  }
  