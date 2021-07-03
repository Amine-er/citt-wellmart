import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';


@Injectable({
  providedIn: 'root'
})
export class MainConfigService {

   private baseUrl: any;
   private ProviderUrlApi = {
     getCountriesUrlPart: '/cities',
     getLoginUrlPart: '/public/v1/users/login',
     getMerchantsPart: '/merchants',
     getProductsPart : '/products'
   };

  constructor() {
    this.baseUrl = environment.baseUrl ;
  }

  public getCountiesrl(): string {
     return this.baseUrl + this.ProviderUrlApi.getCountriesUrlPart;
   }

  public getLoginUrl(): string{
    return this.baseUrl + this.ProviderUrlApi.getLoginUrlPart;
  }
  public getMerchantsUrl() :string{
     return this.baseUrl + this.ProviderUrlApi.getMerchantsPart;
  }
    public getProductsUrl() :string{
     return this.baseUrl + this.ProviderUrlApi.getProductsPart;
  }
    public getCategoriesUrl() :string{
      return this.baseUrl + "/categories";
  }
}
