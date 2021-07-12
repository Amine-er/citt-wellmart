import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';
import { Token } from '../model/token';
import { MainConfigService } from './main-config.service';

@Injectable({
  providedIn: 'root',
})
export class Authenticationervice {
  constructor(
    private httpClient: HttpClient,
    private mainConfig: MainConfigService,
    private router: Router
  ) {}

  public getJwt(): string {
    return sessionStorage.getItem('jwt');
  }
  public isAuthenticated(): boolean {
    return (
      sessionStorage.getItem('jwt') != null &&
      sessionStorage.getItem('jwt') != '' &&
      sessionStorage.getItem('jwt') != undefined
    );
  }
  public extractJwt(token: Token) {
    sessionStorage.setItem('jwt', token.jwt);
  }

  public logout() {
    sessionStorage.removeItem('jwt');
    window.location.replace('/login');
  }
  public getUserName(): string {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem('jwt');
    let username = '';
    if (token) {
      const decodedToken = helper.decodeToken(token);
      username = decodedToken.user;
    }
    return username;
  }
  public getUserId(): string {
    const helper = new JwtHelperService();
    const token = sessionStorage.getItem('jwt');
    let username = '';
    if (token) {
      const decodedToken = helper.decodeToken(token);
      username = decodedToken.userId;
    }
    return username;
  }

  public login(user): Observable<Token> {
    let body = new HttpParams();
    body = body.set('username', user.username);
    body = body.set('password', user.password);
    return this.httpClient.post<Token>(this.mainConfig.getLoginUrl(), body);
  }
}
