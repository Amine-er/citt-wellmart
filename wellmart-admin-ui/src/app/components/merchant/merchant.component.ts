import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Authenticationervice } from 'app/service/authentication.service';
import { Merchnant } from 'app/shared/models/merchant';

@Component({
  selector: 'app-user-profile',
  templateUrl: './merchant.component.html',
  styleUrls: ['./merchant.component.css']
})
export class MerchantComponent implements OnInit {
  panelOpenState = false;
  merchantForm: FormGroup;
  
  constructor(private authenticationervice: Authenticationervice, private router: Router, route: ActivatedRoute,private fb: FormBuilder,) {
    route.params.subscribe(val => {
     if (this.authenticationervice.isAuthenticated()) {
            this.authenticationervice.getPermissions();
        } else {
            this.authenticationervice.logout();
             this.router.navigate(['/login']);
        }
  });
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
    alert(JSON.stringify(merchant))
  }

  formToMerchant(form: FormGroup): Merchnant {
       
       const merchant: Merchnant = new Merchnant();
       merchant.firstName =  form.controls.firstName.value ;
       merchant.lastName =  form.controls.lastName.value ;
       merchant.email =  form.controls.email.value ;
       merchant.address =  form.controls.address.value ;
       merchant.companyName =  form.controls.companyName.value ;
       merchant.companyActivity =  form.controls.companyActivity.value ;
       merchant.merchantType = form.controls.merchantType.value;;
       merchant.phone = form.controls.phone.value ;
       return merchant ;
   }
}
