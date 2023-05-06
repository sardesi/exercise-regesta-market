import { Pagination } from "../model/_interfaces";

export class RegestaMarketConstants {

    public static PRODUCT_PAGE_SIZE: number = 12;
    public static DEFAULT_PRODUCT_ORDER: string = "NAME";
    public static DEFAULT_PRODUCT_PAGINATION: Pagination = {
        limit: RegestaMarketConstants.PRODUCT_PAGE_SIZE,
        offset: 0,
        order: RegestaMarketConstants.DEFAULT_PRODUCT_ORDER
    }

}