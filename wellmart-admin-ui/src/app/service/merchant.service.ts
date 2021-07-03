import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Category } from "app/shared/models/Categorie";
import { City } from "app/shared/models/city";
import { Country } from "app/shared/models/country";
import { Merchnant } from "app/shared/models/merchant";
import { Product } from "app/shared/models/product";
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
      public saveProduct(product: Product): Observable<Product> {
        return this.httpClient.post<Product>(this.mainConfig.getProductsUrl(), product);
    }
    public getAllMerchants():Observable<Merchnant[]> {

    return this.httpClient.get<Merchnant[]>(this.mainConfig.getMerchantsUrl())
  }
  
      public getAllProduct():Observable<Product[]> {

    return this.httpClient.get<Product[]>(this.mainConfig.getProductsUrl())
  }
  
        public getAllCategories():Observable<Category[]> {

    return this.httpClient.get<Category[]>(this.mainConfig.getCategoriesUrl())
  }
}

  