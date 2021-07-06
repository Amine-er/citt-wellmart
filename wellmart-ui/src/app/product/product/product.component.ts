import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/model/product';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  private baseUrl: any;
  products: Product[] = [];
  constructor(
    private route: ActivatedRoute,
    private httpClient: HttpClient,
    private sanitizer: DomSanitizer
  ) {
    this.baseUrl = environment.baseUrl;
  }

  ngOnInit(): void {
    let id = this.route.snapshot.params['id'];
    let res: Product[] = [];
    this.httpClient
      .get<Product[]>(this.baseUrl + '/products/categories/' + id)
      .subscribe((data) => {
        data.forEach((e) => {
          let base64 = e.imageModel.picByte;
          this.sanitizer.bypassSecurityTrustResourceUrl(base64);
          let imge = 'data:image/jpeg;base64,' + base64;
          e.imageUrl = imge;
          console.log(e.imageUrl);
          res.push(e);
        });
        this.products = res;
        console.log(JSON.stringify(this.products));
      });
  }
}
