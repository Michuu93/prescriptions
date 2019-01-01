import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-prescriptions',
  template: `
    <p>
      prescriptions works!
    </p>
  `,
  styles: [],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PrescriptionsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
