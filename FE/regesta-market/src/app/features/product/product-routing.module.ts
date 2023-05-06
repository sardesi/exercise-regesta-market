import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from './views/product-list/product-list.component';
import { CommonModule } from '@angular/common';
import { AuthGuard } from 'src/app/shared/guards/auth.guard';
import { ProductListResolver } from './resolvers/product-list.resolver';

const routes: Routes = [
  { 
    path: '', 
    component: ProductListComponent,
    canActivate: [ AuthGuard ],
    resolve: { resolvedData: ProductListResolver }
  },
];

@NgModule({
  imports: [CommonModule, RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }