import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/Category';
import { MainConfigService } from './main-config.service';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(
    private httpClient: HttpClient,
    private mainConfig: MainConfigService
  ) {}

  public getCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(this.mainConfig.getCategoriesUrl());
  }
}
