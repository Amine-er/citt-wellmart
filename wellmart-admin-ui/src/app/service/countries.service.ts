import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { City } from "app/shared/models/city";
import { Country } from "app/shared/models/country";
import { Observable } from "rxjs";
import { Authenticationervice } from "./authentication.service";
import { MainConfigService } from "./main-config.service";

@Injectable({
    providedIn: 'root'
  })
  export class CountriesService {
  
    
  constructor(private httpClient: HttpClient, private mainConfig: MainConfigService) {
     
    }

  public getCounties(): Observable<City[]> {

    return this.httpClient.get<City[]>(this.mainConfig.getCountiesrl())}
}
  

  