import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

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

  constructor() { }

  ngOnInit() {
  }

}
