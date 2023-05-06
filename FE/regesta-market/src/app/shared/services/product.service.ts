import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { ApiService } from "./api.service";
import { LangService } from "./lang-service";
import { ListResponse, ProductsListRequest, TranslatedProduct } from "../model/_interfaces";

const URL_GET_PRODUCTS = "/api/product/listTranslatedProducts";

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

}