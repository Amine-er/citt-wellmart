

import {HttpClient} from '@angular/common/http';
import {Component, ViewChild, AfterViewInit} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, SortDirection} from '@angular/material/sort';
import { City } from 'app/shared/models/city';
import { Country } from 'app/shared/models/country';
import { CountriesService } from 'app/service/countries.service';
import {merge, Observable, of as observableOf} from 'rxjs';
import {catchError, map, startWith, switchMap} from 'rxjs/operators';
import { MatTableDataSource } from '@angular/material/table';

/**
 * @title Table retrieving data through HTTP
 */
@Component({
  selector: 'app-table-list',
  templateUrl: './references.component.html',
  styleUrls: ['./references.component.css']
})
export class  ReferencesComponent implements AfterViewInit {
  displayedColumns: string[] = ['id', 'name','pays','trash'];
  data: City[] = [];
  dataSource = new MatTableDataSource<City>([]);

 @ViewChild(MatPaginator) paginator: MatPaginator;
  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;


  @ViewChild(MatSort) sort: MatSort;

  constructor(private countriesService: CountriesService) {}

  ngAfterViewInit() {
  this.dataSource.paginator = this.paginator;
    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);
    this.countriesService.getCounties().subscribe(data => {
     this.dataSource.data = data ;
   })
  }
}


