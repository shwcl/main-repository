import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartDetailsComponent } from './components/cart-details/cart-details.component';


const routes: Routes = [

  {path:'category/:id', component:ProductListComponent},
  {path:'products/:id', component:ProductDetailsComponent},
  {path:'category', component:ProductListComponent},
  {path:'products', component:ProductListComponent},
  {path:'cart-details', component:CartDetailsComponent},
  {path:'', redirectTo:'/products', pathMatch:'full'},
  {path:'**', redirectTo:'/products', pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
