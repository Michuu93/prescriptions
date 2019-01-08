import {Component, OnDestroy, OnInit} from '@angular/core';
import {Prescription} from "../model/prescription-model";
import {PrescriptionPrinterStyle} from "./prescription-printer-style";

@Component({
    selector: 'app-prescriptions-template',
    templateUrl: './prescription-preview.component.html',
    styleUrls: ['./prescription-preview.component.scss']
})
export class PrescriptionPreviewComponent implements OnInit, OnDestroy {
    printedPrescription: Prescription;

    constructor() {
    }

    ngOnDestroy(): void {
    }

    ngOnInit(): void {
        this.printedPrescription.prescriptionNumber = "01234567890123456789";
    }

    print() {
        let prescription = document.getElementById("print-area").innerHTML;
        let style = PrescriptionPrinterStyle.printStyle;
        let popupWin = window.open('', '_blank', 'top=0,left=0,height=auto,width=auto');
        popupWin.document.open();
        popupWin.document.write(`
            <html>
                <head><link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></head>
                <style>${style}</style>
                <body onload="window.print();window.close()">${prescription}</body>
            </html>
        `);
        popupWin.document.close();
    }


}