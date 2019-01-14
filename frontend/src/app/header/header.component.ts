import {ChangeDetectionStrategy, Component} from '@angular/core';
import {DrugsUploaderService} from "../drugs/drugs-uploader/drugs-uploader.service";
import {FileUploader} from "ng2-file-upload";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss'],
    providers: [DrugsUploaderService],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderComponent {
    uploader: FileUploader;

    constructor(private drugsUploaderService: DrugsUploaderService) {
        this.uploader = drugsUploaderService.uploader;
    }
}