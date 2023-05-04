export default class StringUtils {

    static isEmpty(value: string | null) {
        return value == null || value.trim() == "";
    }

    static leftPad(value: string | number, size: number, padChar: string = " "): string {
        value = value + "";
        while (value.length < size) value = padChar + value;
        return value;
    }
    
    static rightPad(value: string | number, size: number, padChar: string = " "): string {
        value = value + "";
        while (value.length < size) value = value + padChar;
        return value;
    }

    static isValidEmail(value: string) {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(value.toLowerCase());
    }

    static isCodiceFiscale(value: string) {
        const re = /^([A-Z]{6}[0-9LMNPQRSTUV]{2}[ABCDEHLMPRST]{1}[0-9LMNPQRSTUV]{2}[A-Z]{1}[0-9LMNPQRSTUV]{3}[A-Z]{1})$|([0-9]{11})$/;
        return re.test(value.toUpperCase());
    }

    static isPhoneNumber(value: string) {
        const re = /^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$/;
        return re.test(value);
    }

}
