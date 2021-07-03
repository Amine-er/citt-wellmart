import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../../components/dashboard/dashboard.component';
import { MerchantComponent } from '../../../components/merchant/merchant.component';
import { ReferencesComponent } from '../../../components/references/references.component';
import { MapsComponent } from '../../../components/dashboard/maps/maps.component';
import { NotificationsComponent } from '../../../components/dashboard/notifications/notifications.component';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSelectModule} from '@angular/material/select';
import { MaterialModule } from 'app/material.module';
import { ProductComponent } from 'app/components/product/product.component';



@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatRippleModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MaterialModule,
    MatTooltipModule,
  ],
  declarations: [
    DashboardComponent,
    MerchantComponent,
    ProductComponent,
    ReferencesComponent,
    MapsComponent,
    NotificationsComponent,
  ]
})

export class AdminLayoutModule {}
