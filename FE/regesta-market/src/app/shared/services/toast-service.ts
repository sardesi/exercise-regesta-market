import { Injectable } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MessageService } from 'primeng-lts/api';

const TYPE_ERROR: string = "error";
const TYPE_INFO: string = "info";
const TYPE_SUCCESS: string = "success";
const TYPE_WARNING: string = "warn";

const TOATS_LANG_PREFIX: string = "TOAST.";

const ERROR_TITLE: string = "ERROR_TITLE";
const INFO_TITLE: string = "INFO_TITLE";
const SUCCESS_TITLE: string = "SUCCESS_TITLE";
const WARNING_TITLE: string = "WARNING_TITLE";

@Injectable({
  providedIn: 'root'
})
export class ToastService {

    constructor(
        public messageService: MessageService,
        private translateService: TranslateService
    ) { }

    private toast(message: string, title: string, type: string) {
        this.messageService.add({severity: type, summary: title, detail: message});
    }

    private langToast(message: string, title: string, type: string) {
        this.toast(this.translateService.instant(message), this.translateService.instant(TOATS_LANG_PREFIX + title), type);
    }

    public error(message: string, title: string = ERROR_TITLE, useLang: boolean = true) {
        const method = useLang ? this.langToast : this.toast;
        method.call(this, message, title, TYPE_ERROR);
    }

    public info(message: string, title: string = INFO_TITLE, useLang: boolean = true) {
        const method = useLang ? this.langToast : this.toast;
        method.call(this, message, title, TYPE_INFO);
    }

    public success(message: string, title: string = SUCCESS_TITLE, useLang: boolean = true) {
        const method = useLang ? this.langToast : this.toast;
        method.call(this, message, title, TYPE_SUCCESS);
    }

    public warning(message: string, title: string = WARNING_TITLE, useLang: boolean = true) {
        const method = useLang ? this.langToast : this.toast;
        method.call(this, message, title, TYPE_WARNING);
    }

    public clear() {
        this.messageService.clear();
    }

}