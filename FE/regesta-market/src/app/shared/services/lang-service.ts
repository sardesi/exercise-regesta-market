import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { LocalStorageService } from './local-storage.service';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from './user.service';

const DEFAULT_LANGUAGE: string = "IT";
const LANG_CHANGE_API_URL: string = "/api/utente/cambiaLingua";

@Injectable({
  providedIn: 'root'
})
export class LangService {

    public static LANG_LIST = [
        { chiave: "EN", valore: "English" },
        { chiave: "IT", valore: "Italiano" }
    ];

    private currentLang: string;

    constructor(
        private apiService: ApiService,
        private localStorageService: LocalStorageService,
        private translateService: TranslateService,
        private userService: UserService
    ) {
        const lastUsedLang: string | null = this.localStorageService.get(LocalStorageService.STORAGE_LANGUAGE_KEY);
        this.currentLang =  lastUsedLang ? lastUsedLang : DEFAULT_LANGUAGE;
        this.translateService.setDefaultLang( lastUsedLang ? lastUsedLang : DEFAULT_LANGUAGE );
    }

    public setLanguage(languageId: string, reload: boolean = true) {
        this.localStorageService.set(LocalStorageService.STORAGE_LANGUAGE_KEY, languageId);
        this.translateService.use(languageId);
        this.currentLang = languageId;
        if(reload) window.location.reload();
    }

    public getCurrentLanguage(): string {
        return this.currentLang;
    }

    public switchLanguage() {
        this.setLanguage(this.currentLang == 'IT' ? 'EN' : 'IT');
    }

    public changeLanguage(langId: string) {
        if(this.userService.isLogged()) {
            this.apiService.authGet(LANG_CHANGE_API_URL, { "idLingua": langId }).subscribe(
                () => {
                    this.setLanguage(langId);
                }
            );
        } else {
            this.setLanguage(langId);
        }
    }

    public translate(codiceDizionario: string, parametri?: any) {
        var testo = this.translateService.instant(codiceDizionario);
        if(parametri != null) Object.keys(parametri).forEach( (key: string) => { testo = testo.replace("{{" + key + "}}", parametri[key]) } );
        return testo;
    }

}