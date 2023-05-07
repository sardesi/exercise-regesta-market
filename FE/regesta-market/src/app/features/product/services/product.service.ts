import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ApiService } from "../../../shared/services/api.service";
import { LangService } from "../../../shared/services/lang-service";
import { Discount, ListResponse, PricedProduct, ProductsListRequest, TranslatedProduct } from "../../../shared/model/_interfaces";
import DateUtils from "src/app/shared/utils/date-utils";
import { map } from "rxjs/operators";

const URL_GET_PRODUCTS = "/api/product/listTranslatedProducts";
const URL_GET_PRICED_PRODUCTS = "/api/product/listPricedProducts/";
const URL_GET_DISCOUNTS = "/api/discount/listByDate";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    private _usersUpdateEvent = new Subject<any>();
    get usersUpdateEvent() {
        return this._usersUpdateEvent;
    } 

    constructor(
        private apiService: ApiService,
        private langService: LangService,
    ) { }

    public getProductList(request: ProductsListRequest, hideLoader: boolean = false): Observable<ListResponse<TranslatedProduct>> {
        return this.apiService.authPost(URL_GET_PRODUCTS, request, { language: this.langService.getCurrentLanguage() }, null, null, null, hideLoader);
    }

    public getPricedProducts(productId: number): Observable<PricedProduct[]> {
        return this.apiService.authGet(URL_GET_PRICED_PRODUCTS + productId, null);
    }

    public getDiscountsByDate(date: Date): Observable<Discount[]> {
        return this.apiService.authGet(URL_GET_DISCOUNTS, { date: DateUtils.dateToDateApi(date) }).pipe(
            map( (results: any[]) => {
                results.forEach( x => {
                    x.dateFrom = DateUtils.dateApiToDate(x.dateFrom);
                    x.dateTo = DateUtils.dateApiToDate(x.dateTo);
                });
                return results;
            })
        );
    }

}