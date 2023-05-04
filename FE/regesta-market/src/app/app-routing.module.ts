import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: 'login', 
    loadChildren: () => import('./features/login/login.module').then(m => m.LoginModule),
  },
  // {
  //   path: 'products', 
  //   loadChildren: () => import('./features/products/products.module').then(m => m.ProductsModule),
  // },
  {
    path: '**',
    redirectTo: 'login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }