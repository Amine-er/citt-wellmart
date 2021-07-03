import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { City } from "app/shared/models/city";
import { Country } from "app/shared/models/country";
import { Merchnant } from "app/shared/models/merchant";
import { Observable } from "rxjs";
import { Authenticationervice } from "./authentication.service";
import { MainConfigService } from "./main-config.service";

@Injectable({
    providedIn: 'root'
})
export class MerchantService {
  
    
    constructor(private httpClient: HttpClient, private mainConfig: MainConfigService) {
     
    }

    public saveMerchant(merchant: Merchnant): Observable<Merchnant> {
        return this.httpClient.post<Merchnant>(this.mainConfig.getMerchantsUrl(), merchant);
    }
    public getAllMerchants():Observable<Merchnant[]> {

    return this.httpClient.get<Merchnant[]>(this.mainConfig.getMerchantsUrl())
  }
  
}

  