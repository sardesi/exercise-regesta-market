import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

const TYPE_ERROR: string = "toast-error";
const TYPE_INFO: string = "toast-info";
const TYPE_SUCCESS: string = "toast-success";
const TYPE_WARNING: string = "toast-warning";

const TOATS_LANG_PREFIX: string = "TOAST.";

const ERROR_TITLE: string = "ERROR_TITLE";
const INFO_TITLE: string = "INFO_TITLE";
const SUCCESS_TITLE: string = "SUCCESS_TITLE";
const WARNING_TITLE: string = "WARNING_TITLE";






// TODO: Rivedere in base a PrimeNG







@Injectable({
  providedIn: 'root'
})
export class MessageService {

    constructor(
        // private toastrService: ToastrService,
        private translateService: TranslateService
    ) { }

    // private toast(message: string, title: string, type: string) {
    //     this.toastrService.show(message, title, undefined, type);
    // }

    // private langToast(message: string, title: string, type: string) {
    //     this.toast(this.translateService.instant(message), this.translateService.instant(TOATS_LANG_PREFIX + title), type);
    // }

    // public error(message: string, title: string = ERROR_TITLE, useLang: boolean = true) {
    //     const method = useLang ? this.langToast : this.toast;
    //     method.call(this, message, title, TYPE_ERROR);
    // }

    // public info(message: string, title: string = INFO_TITLE, useLang: boolean = true) {
    //     const method = useLang ? this.langToast : this.toast;
    //     method.call(this, message, title, TYPE_INFO);
    // }

    // public success(message: string, title: string = SUCCESS_TITLE, useLang: boolean = true) {
    //     const method = useLang ? this.langToast : this.toast;
    //     method.call(this, message, title, TYPE_SUCCESS);
    // }

    // public warning(message: string, title: string = WARNING_TITLE, useLang: boolean = true) {
    //     const method = useLang ? this.langToast : this.toast;
    //     method.call(this, message, title, TYPE_WARNING);
    // }

}