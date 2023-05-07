import { Component, Input } from '@angular/core';
import { fader } from 'src/app/shared/animations/animations';
import {  Discount, PricedProduct, TranslatedProduct } from 'src/app/shared/model/_interfaces';
import { ProductService } from 'src/app/features/product/services/product.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss'],
  animations: [ fader ]
})
export class ProductDetailComponent {

  product: TranslatedProduct;
  @Input() set selectedProduct(selectedProduct: TranslatedProduct) {
    this.product = selectedProduct;
    this.date = new Date();
    this.date.setHours(0, 0, 0, 0);
    this.quantity = 1;
    this.searchPrices();
  }

  date: Date = new Date();
  quantity: number = 1;

  pricedProducts: PricedProduct[] = [];
  discounts: Discount[] = [];

  bestPrice: any;
  bestShipping: any;
  
  constructor(
    private productService: ProductService,
  ) { }

  searchPrices() {
    this.pricedProducts = [];
    forkJoin({
      products: this.productService.getPricedProducts(this.product.id),
      discounts: this.productService.getDiscountsByDate(this.date)
    }).subscribe( results => {
      this.discounts = results.discounts;
      this.calculatePrices(results.products);
    });
  }

  calculatePrices(products: PricedProduct[]) {

    this.bestPrice = {};
    this.bestShipping = {};

    let activeList: PricedProduct[] = [];
    let inactiveList: PricedProduct[] = [];

    products.forEach( x => {

      x.fullPrice = x.price * this.quantity;
      x.discountedPrice = this.getDiscounts(x);
      x.inactive = x.availability < this.quantity;
      
      if(x.inactive) {
        inactiveList.push(x);
      } else {
        if(!this.bestPrice.value || x.discountedPrice < this.bestPrice.value) this.bestPrice = { id: x.supplierId, value: x.discountedPrice };
        if(!this.bestShipping.value || x.shippingDays <= this.bestShipping.value) this.bestShipping = { id: x.supplierId, value: x.shippingDays };
        activeList.push(x);
      }
      
    });

    activeList.sort( (a, b) => {
      if(a.discountedPrice > b.discountedPrice) return 1;
      else if(b.discountedPrice > a.discountedPrice) return -1;
      else return a.shippingDays >= b.shippingDays ? 1 : -1;
    });

    inactiveList.sort( (a, b) => {
      if(a.discountedPrice > b.discountedPrice) return 1;
      else if(b.discountedPrice > a.discountedPrice) return -1;
      else return a.shippingDays >= b.shippingDays ? 1 : -1;
    });

    this.pricedProducts = [ ...activeList, ...inactiveList ];

  }

  private getDiscounts(product: PricedProduct): number {
    let bestDiscount: number = 0;
    for(let x of this.discounts) {
      if(x.supplierId != product.supplierId) continue;
      if(!(this.date.getTime() >= x.dateFrom.getTime() && this.date.getTime() <= x.dateTo.getTime())) continue;
      if(x.minPieceDiscount != null && this.quantity < x.minPieceDiscount) continue; 
      if(x.priceTo != null && product.fullPrice > x.priceTo ) continue;
      if(x.priceFrom != null && product.fullPrice < x.priceFrom ) continue;
      if(bestDiscount < x.percentage) bestDiscount = x.percentage;
    };
    return product.fullPrice - (product.fullPrice * (bestDiscount / 100));
  }

}
