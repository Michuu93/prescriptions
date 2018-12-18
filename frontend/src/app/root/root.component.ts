import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';

@Component({
    selector: 'app-root',
    templateUrl: 'root.component.html',
    styles: [],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class RootComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}
