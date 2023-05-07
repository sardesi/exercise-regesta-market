import StringUtils from "./string-utils";

export default class DateUtils {

    static dateToDateApi(date: Date): string {
        if(!date) return null;
        return date.getFullYear() + "-" + StringUtils.leftPad(date.getMonth() + 1, 2, "0") + "-" + StringUtils.leftPad(date.getDate(), 2, "0");
    }

    static dateApiToDate(date: any[]): Date {
        if(!date) return null;
        return new Date(date[0], date[1] - 1, date[2]);
    }

}
