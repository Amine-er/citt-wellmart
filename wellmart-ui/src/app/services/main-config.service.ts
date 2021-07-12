import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class MainConfigService {
  private baseUrl: any;
  private ProviderUrlApi = {
    getCategoryUrlPart: '/categories',
    getLoginUrlPart: '/public/v1/users/login',
  };

  constructor() {
    this.baseUrl = environment.baseUrl;
  }

  public getCategoriesUrl(): string {
    return this.baseUrl + this.ProviderUrlApi.getCategoryUrlPart;
  }
  public getLoginUrl(): string {
    return this.baseUrl + this.ProviderUrlApi.getLoginUrlPart;
  }
}
