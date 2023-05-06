import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { ProductListComponent } from './views/product-list/product-list.component';
import { ProductRoutingModule } from './product-routing.module';

@NgModule({
  declarations: [
    ProductListComponent,
  ],
  imports: [
    ProductRoutingModule,
    SharedModule,
  ]
})
export class ProductModule { }
