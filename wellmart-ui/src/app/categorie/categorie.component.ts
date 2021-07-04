import { Component, OnInit } from '@angular/core';
import { Category } from '../model/Category';
import { CategoryService } from '../services/countries.service';

@Component({
  selector: 'app-categorie',
  templateUrl: './categorie.component.html',
  styleUrls: ['./categorie.component.css'],
})
export class CategorieComponent implements OnInit {
  constructor(private categoryService: CategoryService) {}
  categories: any = [];

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe((response) => {
      this.categories = response;
    });
  }
}
