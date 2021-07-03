import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Authenticationervice } from 'app/service/authentication.service';
import { MerchantService } from 'app/service/merchant.service';
import { Merchnant } from 'app/shared/models/merchant';

@Component({
  selector: 'app-user-profile',
  templateUrl: './merchant.component.html',
  styleUrls: ['./merchant.component.css']
})
export class MerchantComponent implements OnInit,AfterViewInit {
  panelOpenState = false;
  merchantForm: FormGroup;
    data: Merchnant[] = [];
  dataSource = new MatTableDataSource<Merchnant>([]);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  displayedColumns: string[] = ['merchantType', 'firstName','lastName','name','activity','phone','email','address','webSite','trash'];
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;


  @ViewChild(MatSort) sort: MatSort;

  constructor(private authenticationervice: Authenticationervice, private router: Router, route: ActivatedRoute,private fb: FormBuilder,private merchantService:MerchantService) {
    route.params.subscribe(val => {
     if (this.authenticationervice.isAuthenticated()) {
            this.authenticationervice.getPermissions();
        } else {
            this.authenticationervice.logout();
             this.router.navigate(['/login']);
        }
  });
  }
  ngAfterViewInit(): void {
      this.dataSource.paginator = this.paginator;
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.merchantService.getAllMerchants().subscribe(data => {
     this.dataSource.data = data ;
   })
  }

  ngOnInit() {
    this.createForm()
  }

    createForm() {
    this.merchantForm = this.fb.group({
      merchantType:  ['', [Validators.required]],
      firstName:  ['', Validators.required],
      lastName: ['', Validators.required],
      companyName: ['', Validators.required],
      companyActivity: ['', Validators.required],
      phone: ['', Validators.required],
      email:  ['',Validators.email],
      address: ['', Validators.required],
      webSite: ['']
    }
      );
    }
  
  onCreateMerchant() {
    let merchant = this.formToMerchant(this.merchantForm);
    this.merchantService.saveMerchant(merchant).subscribe(rep => {
      alert("Merchant created")
      this.merchantService.getAllMerchants().subscribe(data => {
     this.dataSource.data = data ;
   })

    }, err => {
      alert(JSON.stringify(err))
    })
  }

  formToMerchant(form: FormGroup): Merchnant {
       
       const merchant: Merchnant = new Merchnant();
       merchant.firstName =  form.controls.firstName.value ;
       merchant.lastName =  form.controls.lastName.value ;
       merchant.email =  form.controls.email.value ;
       merchant.address =  form.controls.address.value ;
       merchant.name =  form.controls.companyName.value ;
       merchant.activity =  form.controls.companyActivity.value ;
       merchant.merchantType = form.controls.merchantType.value;;
    merchant.phone = form.controls.phone.value;
    merchant.webSite = form.controls.webSite.value;
       return merchant ;
   }
}
