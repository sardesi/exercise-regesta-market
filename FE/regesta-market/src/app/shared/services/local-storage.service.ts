import { Injectable } from '@angular/core';
 
const STORAGE_PREFIX = "regesta_market_";

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

    public static STORAGE_LANGUAGE_KEY: string = STORAGE_PREFIX + "_lang";
    public static STORAGE_JWT_TOKEN: string = STORAGE_PREFIX + "_jwt";
    public static STORAGE_HAS_BEEN_HERE: string = STORAGE_PREFIX + "_hasbeenhere";
 
    set(key: string, value: string) {
        localStorage.setItem(key, value);
    }
 
    get(key: string) : string | null {
        return localStorage.getItem(key);
    }
 
    remove(key: string) {
        localStorage.removeItem(key);
    }

}