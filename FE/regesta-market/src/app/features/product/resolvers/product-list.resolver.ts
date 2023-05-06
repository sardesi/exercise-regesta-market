import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { forkJoin, of } from 'rxjs';
import { ProductService } from 'src/app/shared/services/product.service';
import { RegestaMarketConstants } from 'src/app/shared/constants/regesta-market-constants';

@Injectable({
  providedIn: 'root'
})
export class ProductListResolver implements Resolve<any> {

  constructor(
    private productService: ProductService,
  ) {}

  resolve() {

    return forkJoin(
      {
        productList: this.productService.getProductList({ pagination: RegestaMarketConstants.DEFAULT_PRODUCT_PAGINATION }),
      }
    );
    
  }
  
}
