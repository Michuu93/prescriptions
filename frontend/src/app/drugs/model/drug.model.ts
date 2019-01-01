import {Refund} from "./refund.model";

export class Drug {
    public bl7: string;
    public ean: string;
    public psychotrope: boolean;
    public senior: boolean;
    public vaccine: boolean;
    public price: number;
    public name: string;
    public internationalName: string;
    public form: string;
    public dose: string;
    public packageSize: string;
    public refunds: Refund[];

    constructor(bl7: string, ean: string, psychotrope: boolean, senior: boolean, vaccine: boolean, price: number, name: string, internationalName: string, form: string, dose: string, packageSize: string, refunds: Refund[]) {
        this.bl7 = bl7;
        this.ean = ean;
        this.psychotrope = psychotrope;
        this.senior = senior;
        this.vaccine = vaccine;
        this.price = price;
        this.name = name;
        this.internationalName = internationalName;
        this.form = form;
        this.dose = dose;
        this.packageSize = packageSize;
        this.refunds = refunds;
    }
}