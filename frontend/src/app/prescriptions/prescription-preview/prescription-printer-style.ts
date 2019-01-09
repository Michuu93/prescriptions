export class PrescriptionPrinterStyle {
    static printStyle = `
.prescription-body {
  width: 99mm;
  height: 210mm;
  border: black solid 1px;
  display: block;
}
.prescription-body .prescription-header {
  height: 15%;
  border-bottom: black solid 1px;
}
.prescription-body .prescription-header .header-left {
  float: left;
  height: calc(100% - 1mm);
  width: calc(50% - 1mm);
  margin: 1mm 0 0 1mm;
}
.prescription-body .prescription-header .header-right {
  float: right;
  font-size: 13pt;
  height: calc(100% - 1mm);
  width: calc(50% - 1mm);
  text-align: right;
  margin: 1mm 1mm 0 0;
}
.prescription-body .prescription-patient {
  height: 25%;
  border-bottom: black solid 1px;
}
.prescription-body .prescription-patient .patient-left {
  position: relative;
  float: left;
  width: 70%;
  height: 100%;
}
.prescription-body .prescription-patient .patient-left .patient-title {
  font-size: 8pt;
  margin: 0 0 0 1mm;
}
.prescription-body .prescription-patient .patient-left .patient-value {
  margin: 10% 0 0 1mm;
}
.prescription-body .prescription-patient .patient-left .patient-footer {
  position: absolute;
  bottom: 0;
  width: calc(100% - 2mm);
  margin: 1mm;
}
.prescription-body .prescription-patient .patient-left .patient-footer .patient-footer-left {
  float: left;
  height: 100%;
  width: 50%;
}
.prescription-body .prescription-patient .patient-left .patient-footer .patient-footer-right {
  float: right;
  height: 100%;
  width: 50%;
  text-align: right;
}
.prescription-body .prescription-patient .patient-right {
  float: right;
  width: 30%;
  height: 100%;
}
.prescription-body .prescription-patient .patient-right .nfz {
  width: 100%;
  height: 50%;
  border-left: black solid 1px;
  border-bottom: black solid 1px;
  font-size: 8pt;
}
.prescription-body .prescription-patient .patient-right .nfz .nfz-title {
  margin: 0 0 0 1mm;
}
.prescription-body .prescription-patient .patient-right .nfz .nfz-value {
  font-size: 18pt;
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.prescription-body .prescription-patient .patient-right .permissions {
  width: 100%;
  height: 50%;
  border-left: black solid 1px;
  font-size: 8pt;
}
.prescription-body .prescription-patient .patient-right .permissions .permissions-title {
  margin: 0 0 0 1mm;
}
.prescription-body .prescription-patient .patient-right .permissions .permissions-value {
  font-size: 18pt;
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.prescription-body .prescription-drugs {
  height: 40%;
  border-bottom: black solid 1px;
}
.prescription-body .prescription-drugs .drugs-header {
  width: 100%;
  height: 5%;
  font-size: 8pt;
  border-bottom: black dashed 1px;
}
.prescription-body .prescription-drugs .drugs-header .drugs-header-left {
  float: left;
  width: calc(85% - 2mm);
  height: 100%;
  margin: 0 0 0 1mm;
}
.prescription-body .prescription-drugs .drugs-header .drugs-header-right {
  border-left: black dashed 1px;
  float: right;
  width: 15%;
  height: 100%;
}
.prescription-body .prescription-drugs .drugs-item {
  border-bottom: black dashed 1px;
  height: 15%;
  font-size: 10pt;
}
.prescription-body .prescription-drugs .drugs-item .drugs-item-left {
  float: left;
  width: calc(85% - 3mm);
  height: 100%;
  margin: 0 1mm;
}
.prescription-body .prescription-drugs .drugs-item .drugs-item-right {
  font-size: 8pt;
  border-left: black dashed 1px;
  float: right;
  width: calc(15% - 1mm);
  height: 100%;
  margin: 0 1mm 0 0;
}
.prescription-body .prescription-drugs .prescription-number-barcode {
  margin: 1mm;
}
.prescription-body .prescription-footer {
  height: calc(20% - 1mm);
}
.prescription-body .prescription-footer .footer-left {
  float: left;
  width: 30%;
  height: 100%;
}
.prescription-body .prescription-footer .footer-left .date {
  width: 100%;
  height: 50%;
  border-right: black solid 1px;
  border-bottom: black solid 1px;
  font-size: 8pt;
}
.prescription-body .prescription-footer .footer-left .date .date-title {
  margin: 0 0 0 1mm;
}
.prescription-body .prescription-footer .footer-left .date .date-value {
  font-size: 14pt;
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.prescription-body .prescription-footer .footer-left .date-from-day {
  width: 100%;
  height: 50%;
  border-right: black solid 1px;
  font-size: 8pt;
}
.prescription-body .prescription-footer .footer-left .date-from-day .date-from-day-title {
  margin: 0 0 0 1mm;
}
.prescription-body .prescription-footer .footer-left .date-from-day .date-from-day-value {
  font-size: 14pt;
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.prescription-body .prescription-footer .footer-right {
  float: right;
  width: 70%;
  height: 100%;
}


.print-area {
    padding: 10px;
}
@page {
    size: A4;
    margin: 0;
}
    `;
}