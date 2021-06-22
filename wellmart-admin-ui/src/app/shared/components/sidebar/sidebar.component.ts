import { Component, OnInit } from '@angular/core';
import { NgxPermissionsService } from 'ngx-permissions';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'dashboard', class: '' },
    { path: '/merchants', title: 'Merchant Profile',  icon:'person', class: '' },
    { path: '/references', title: 'References',  icon:'content_paste', class: '' },
    { path: '/typography', title: 'Products',  icon:'shopping_cart', class: '' },
    { path: '/icons', title: 'Orders',  icon:'shopping_basket', class: '' },
/*     { path: '/icons', title: 'Reference',  icon:'bubble_chart', class: '' },
    { path: '/maps', title: 'Maps',  icon:'location_on', class: '' },
    { path: '/notifications', title: 'Notifications',  icon:'notifications', class: '' },
    { path: '/upgrade', title: 'Upgrade to PRO',  icon:'unarchive', class: 'active-pro' }, */
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  displayMenu = false;
  constructor(private permissionsService: NgxPermissionsService) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.displayMenu = this.permissionsService.getPermission('get_references')==undefined
  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
}
