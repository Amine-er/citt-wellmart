import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import * as bcrypt from 'bcrypt';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../model/Customer';
import { environment } from 'src/environments/environment';

declare var $: any;

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css'],
})
export class InscriptionComponent implements OnInit {
  url: any = 'http://localhost:8080';
  private baseUrl: any;
  customerForm: FormGroup | undefined;

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.baseUrl = environment.baseUrl;
  }

  onCreateCustomer() {
    let customer = this.formToCustomer(this.customerForm);
    this.httpClient
      .post<Customer>(this.baseUrl + '/customers', customer)
      .subscribe(
        (data) => {
          alert('Customer Created with success. '); // TODO to be changed by a popup
          this.router.navigate(['login']);
        },
        (error) => {
          alert('Customer account already exist !');
        }
      );
  }
  ngOnInit(): void {
    this.createForm();
  }

  formToCustomer(form: FormGroup): Customer {
    const customer: Customer = new Customer();
    customer.name = form.controls.prenom.value;
    customer.email = form.controls.email.value;
    customer.phone = form.controls.tel.value;
    customer.password = form.controls.mdp.value;
    return customer;
  }

  createForm() {
    this.customerForm = this.fb.group({
      prenom: ['', [Validators.required]],
      email: ['', Validators.email],
      tel: ['', Validators.required],
      mdp: ['', Validators.required],
    });
  }

  async sInscrire(value: any) {
    if (
      value.email.length != 0 &&
      value.prenom.length != 0 &&
      value.tel.length != 0 &&
      value.mdp.length != 0
    ) {
      this.save(this.url + '/clients', value).subscribe(
        (res) => {
          console.log(res);

          $('#Modal').modal('show');
        },
        (err) => {
          console.log(err);
        }
      );
    } else {
      alert('Veuillez remplir tous les champs !');
    }
  }

  public save(url: any, data: any) {
    return this.httpClient.post(url, data);
  }
}
