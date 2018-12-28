import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import {Office} from "./office.model";

@Component({
  selector: 'app-office',
  template: `
    <p>
      office works!
    </p>
  `,
  styles: [],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OfficeComponent implements OnInit {
  officeData: Office;

  constructor() { }

  ngOnInit() {
  }

}
