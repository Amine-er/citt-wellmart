import { HttpInterceptor, HttpSentEvent, HttpHeaderResponse, HttpHandler, HttpEvent, HttpRequest, HttpHeaders, HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { Injectable } from "@angular/core";
import { tap, catchError } from "rxjs/operators";
import { ErrorObservable } from "rxjs/observable/ErrorObservable";
import { Authenticationervice } from "./authentication.service";


@Injectable()
export class AddTokenInterceptor implements HttpInterceptor {
  
  constructor(private http: HttpClient,private authenticationervice:Authenticationervice){
    
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log(`AddTokenInterceptor - ${req.url}`);
    
    let jsonReq: HttpRequest<any> = req.clone({
      setHeaders:{
        Authorization : `Bearer ${sessionStorage.getItem("jwt")}`
      }
    });
    
    return next.handle(jsonReq);
  }
  
}