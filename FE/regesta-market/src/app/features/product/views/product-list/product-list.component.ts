import { AfterViewInit, Component, OnDestroy, OnInit, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { fader } from 'src/app/shared/animations/animations';
import { ListResponse, Pagination, TranslatedProduct } from 'src/app/shared/model/_interfaces';
import { LangService } from 'src/app/shared/services/lang-service';
import { ProductService } from 'src/app/features/product/services/product.service';
import { RegestaMarketConstants } from 'src/app/shared/constants/regesta-market-constants';

@Component({
  selector: 'product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
  animations: [ fader ]
})
export class ProductListComponent implements AfterViewInit, OnDestroy {

  faderTrigger: boolean = false;
  filters: any = {};
  firstLoad: boolean = false;
  listener;
  mainLayoutElement: Element;
  showScrollToTop: boolean = false;

  pagination: Pagination;
  products: TranslatedProduct[] = [];
  
  availableOrders: any[] = [];
  order: any;

  selectedProduct: TranslatedProduct;
  showDetail: boolean = false;
  
  constructor(
    private route: ActivatedRoute,
    private langService: LangService,
    private productService: ProductService,
    private renderer2: Renderer2
  ) { 

    this.faderTrigger = !this.faderTrigger;

    this.availableOrders = [
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.CODE') + " (A-Z)", code: 'CODE'},
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.CODE') + " (Z-A)", code: 'CODE', desc: true },
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.NAME') + " (A-Z)", code: 'NAME'},
      { name: langService.translate('DROPDOWNS.PRODUCT_FILTER.NAME') + " (Z-A)", code: 'NAME', desc: true }
    ];
    this.order = this.availableOrders[2];

    this.pagination = RegestaMarketConstants.DEFAULT_PRODUCT_PAGINATION;
    this.searchProducts();

  }

  ngAfterViewInit(): void {
    let scrollsList = document.getElementsByClassName('main-layout-content');
    if(scrollsList && scrollsList[0]) {
      this.mainLayoutElement = scrollsList[0];
      this.listener = this.renderer2.listen(this.mainLayoutElement, 'scroll', (e) => {
        this.showScrollToTop = (e.target as Element).scrollTop >= 500;
      });
    }
  }
  
  ngOnDestroy(): void {
    if(this.listener) this.listener();
  }

  searchProducts() {
    this.pagination.offset = 0;
    this.products = [];
    this.firstLoad = true;
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
      if(this.pagination.offset == 0) this.faderTrigger = !this.faderTrigger;
      this.firstLoad = false;
      this.pagination.offset = this.products.length;
    });
  }

  openDetail(product: TranslatedProduct) {
    this.selectedProduct = product;
    this.showDetail = true;
  }

  scrollToTop() {
    this.mainLayoutElement.scrollTo({top: 0, behavior: 'smooth'});
  }

}
