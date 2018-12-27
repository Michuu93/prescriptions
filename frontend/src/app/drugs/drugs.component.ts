import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';

@Component({
    selector: 'app-drugs',
    templateUrl: './drugs.component.html',
    styleUrls: ['./drugs.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class DrugsComponent implements OnInit {

    constructor() {
    }

    ngOnInit() {
    }

}