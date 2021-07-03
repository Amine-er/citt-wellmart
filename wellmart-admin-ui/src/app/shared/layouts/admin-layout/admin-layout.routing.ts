import { Routes } from '@angular/router';

import { DashboardComponent } from '../../../components/dashboard/dashboard.component';
import { MerchantComponent } from '../../../components/merchant/merchant.component';
import { ReferencesComponent } from '../../../components/references/references.component';
import { MapsComponent } from '../../../components/dashboard/maps/maps.component';
import { NotificationsComponent } from '../../../components/dashboard/notifications/notifications.component';
import { LoginComponent } from 'app/components/user/login/login.component';
import { ProductComponent } from 'app/components/product/product.component';

export const AdminLayoutRoutes: Routes = [
    // {
    //   path: '',
    //   children: [ {
    //     path: 'dashboard',
    //     component: DashboardComponent
    // }]}, {
    // path: '',
    // children: [ {
    //   path: 'userprofile',
    //   component: UserProfileComponent
    // }]
    // }, {
    //   path: '',
    //   children: [ {
    //     path: 'icons',
    //     component: IconsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'notifications',
    //         component: NotificationsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'maps',
    //         component: MapsComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'typography',
    //         component: TypographyComponent
    //     }]
    // }, {
    //     path: '',
    //     children: [ {
    //         path: 'upgrade',
    //         component: UpgradeComponent
    //     }]
    // }
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'merchants',   component: MerchantComponent },
    { path: 'references',     component: ReferencesComponent },
    { path: 'maps',           component: MapsComponent },
    { path: 'notifications', component: NotificationsComponent },
    { path: 'login', component: LoginComponent },
       { path: 'products',     component: ProductComponent },
];
