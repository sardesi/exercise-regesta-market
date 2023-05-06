import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { fader } from 'src/app/shared/animations/animations';
import { ListResponse, Pagination, TranslatedProduct } from 'src/app/shared/model/_interfaces';
import { LangService } from 'src/app/shared/services/lang-service';
import { ProductService } from 'src/app/shared/services/product.service';

@Component({
  selector: 'product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
  animations: [ fader ]
})
export class ProductListComponent {

  faderTrigger: number = 0;
  filters: any = {};

  pagination: Pagination;
  products: TranslatedProduct[] = [];
  selectedProduct: TranslatedProduct;
  
  availableOrders: any[] = [];
  order: any;
  
  constructor(
    private route: ActivatedRoute,
    private langService: LangService,
    private productService: ProductService
  ) { 

    const resolvedData = this.route.snapshot.data.resolvedData;
    let productListResponse: ListResponse<TranslatedProduct> = resolvedData.productList;

    this.pagination = productListResponse.pagination;
    this.products = productListResponse.results;
    this.pagination.offset = this.products.length;
    this.faderTrigger++;

    this.availableOrders = [
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.CODE') + " (A-Z)", code: 'CODE'},
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.CODE') + " (Z-A)", code: 'CODE', desc: true },
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.NAME') + " (A-Z)", code: 'NAME'},
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.NAME') + " (Z-A)", code: 'NAME', desc: true }
    ];
    this.order = this.availableOrders[2];

  }

  searchProducts() {
    this.pagination.offset = 0;
    this.products = [];
    this.loadNextPage();
  }
  
  changeOrder() {
    this.pagination.order = this.order.code;
    this.pagination.desc = this.order.desc;
    this.searchProducts();
  }

  loadNextPage(hideLoader: boolean = false) {
    this.productService.getProductList({ pagination: this.pagination, code: this.filters.code, name: this.filters.name }, hideLoader).subscribe( response => {
      this.pagination = response.pagination;
      this.products.push(...response.results);
      if(this.pagination.offset == 0) this.faderTrigger++;
      this.pagination.offset = this.products.length;
    });
  }

  openDetail(product: TranslatedProduct) {
    this.selectedProduct = product;
  }

}
