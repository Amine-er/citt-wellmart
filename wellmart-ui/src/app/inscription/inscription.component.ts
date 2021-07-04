import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import * as bcrypt from 'bcrypt';


declare var $: any;


@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

   
  url:any = "http://localhost:8080";

  
  constructor(private httpClient:HttpClient,private router:Router) { }

  ngOnInit(): void {
  }


  async sInscrire(value:any){

    if(value.email.length != 0 && value.prenom.length != 0 && value.tel.length != 0 && value.mdp.length != 0){
    


    this.save(this.url+"/clients",value).subscribe(res=>{console.log(res);

    
      
      $("#Modal").modal('show');
    

    
    },err=>{console.log(err)})

  }else{

    alert('Veuillez remplir tous les champs !');

  }

  }

  public save(url:any,data:any){

    return this.httpClient.post(url,data);


  }

}
