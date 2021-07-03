import { HttpClient } from '@angular/common/http';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Authenticationervice } from 'app/service/authentication.service';
import { MerchantService } from 'app/service/merchant.service';
import { Category } from 'app/shared/models/Categorie';
import { Merchnant } from 'app/shared/models/merchant';
import { Product } from 'app/shared/models/product';
import { environment } from 'environments/environment';

@Component({
  selector: 'app-user-profile',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit,AfterViewInit {
  panelOpenState = false;
  merchantForm: FormGroup;
    data: Product[] = [];
  dataSource = new MatTableDataSource<Product>([]);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['id', 'category','name','price','description','image','trash'];
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;
  categories : Category[] = []

  @ViewChild(MatSort) sort: MatSort;

  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;
  private baseUrl: any;
  imageId: string;

  constructor(private sanitizer:DomSanitizer,private httpClient: HttpClient,private authenticationervice: Authenticationervice, private router: Router, route: ActivatedRoute,private fb: FormBuilder,private merchantService:MerchantService) {
    route.params.subscribe(val => {
     if (this.authenticationervice.isAuthenticated()) {
            this.authenticationervice.getPermissions();
        } else {
            this.authenticationervice.logout();
             this.router.navigate(['/login']);
        }
    });
      this.baseUrl = environment.baseUrl ;
  }
  ngAfterViewInit(): void {
      this.dataSource.paginator = this.paginator;
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.merchantService.getAllProduct().subscribe(data => {
      let res = [];

      data.forEach(e => {
        let base64 = e.imageModel.picByte;
        this.sanitizer.bypassSecurityTrustResourceUrl(base64);
        let imge = "data:image/jpeg;base64," + base64;
        e.imageUrl = imge;
        console.log(e.imageUrl)
        res.push(e)
      })
     this.dataSource.data = res;
    })
    
    this.merchantService.getAllCategories().subscribe(data => {
      this.categories = data;
    })
  }

  ngOnInit() {
    this.createForm()
    
  }

    createForm() {
    this.merchantForm = this.fb.group({
      category:  ['', [Validators.required]],
      name:  ['', Validators.required],
      price: ['', Validators.required],
      description: ['', Validators.required],
    }
      );
    }
  
  onCreateProduct() {
    let product = this.formToProduct(this.merchantForm);
    //alert(JSON.stringify(product))
    this.merchantService.saveProduct(product).subscribe(data => {
      alert("Product created")
      this.getAllProduct()
    }, err => {
      alert("Product already exist !")
    }, () => {
      location.reload();
    })
   
  }

  getAllProduct() {
        this.merchantService.getAllProduct().subscribe(data => {
     this.dataSource.data = data ;
    })
  }
  formToProduct(form: FormGroup): Product {
       const product: Product = new Product();
       product.name =  form.controls.name.value ;
       product.price =  form.controls.price.value ;
       product.description =  form.controls.description.value ;
       product.categoryId = form.controls.category.value;
    product.merchantUserName = this.authenticationervice.getUserName();
    product.imageUrl = this.imageId;
       return product ;
  }
  
    //Gets called when the user selects an image
  public onFileChanged(event) {
    //Select File
    this.selectedFile = event.target.files[0];
  }

    onUpload() {
    console.log(this.selectedFile);
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);
    this.httpClient.post(this.baseUrl+'/image/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
        if (response.status === 200) {
          this.message = 'Image uploaded successfully';
          this.imageId = response.body.toString();
        } else {
          this.message = 'Image not uploaded successfully';
        }
      }
      );
  }
}
