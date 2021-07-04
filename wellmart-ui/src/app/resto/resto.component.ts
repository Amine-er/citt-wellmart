import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-resto',
  templateUrl: './resto.component.html',
  styleUrls: ['./resto.component.css']
})
export class RestoComponent implements OnInit {

  resto:any;
  test:boolean = false;
  

  constructor(private httpClient:HttpClient) { }

  ngOnInit(): void {

    this.httpClient.get("http://localhost:8080/restoes")
    .subscribe(data=>{

      this.resto = data;

    },err=>{

      console.log(err);
    })

  }

  


}
