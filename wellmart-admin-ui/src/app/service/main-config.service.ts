import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';


@Injectable({
  providedIn: 'root'
})
export class MainConfigService {

   private baseUrl: any;
   private ProviderUrlApi = {
     getCountriesUrlPart: '/cities'
   };

  constructor() {
    this.baseUrl = environment.baseUrl ;
  }

  public getCountiesrl(): string {
     return this.baseUrl + this.ProviderUrlApi.getCountriesUrlPart;
   }

}
