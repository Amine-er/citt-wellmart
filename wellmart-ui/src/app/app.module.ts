import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { CategorieComponent } from './categorie/categorie.component';
import { RestoComponent } from './resto/resto.component';
import { FooterComponent } from './footer/footer.component';
import { RestoDetailComponent } from './resto-detail/resto-detail.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { InscriptionComponent } from './inscription/inscription.component';
import { LocalisationComponent } from './localisation/localisation.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InscriptionSuccesComponent } from './inscription-succes/inscription-succes.component';
import { LogoutComponent } from './logout/logout.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { ProductComponent } from './product/product/product.component';

const appRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: '', component: CategorieComponent },

  { path: 'localisation', component: LocalisationComponent },
  { path: 'logout', component: LogoutComponent },

  { path: 'product', component: ProductComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    CategorieComponent,
    RestoComponent,
    FooterComponent,
    RestoDetailComponent,
    LoginComponent,
    InscriptionComponent,
    LocalisationComponent,
    InscriptionSuccesComponent,
    LogoutComponent,
    ConnexionComponent,
    ProductComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    FormsModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
