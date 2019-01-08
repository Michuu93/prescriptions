export class PrescriptionPrinterStyle {
    static printStyle = `
            .print-area {
                padding: 10px;
            }
            .prescription-body {
                width: 99mm;
                height: 210mm;
                border: black solid 2px;
            }
            @page {
                size: auto;
                margin: 0;
            }
    `;
}