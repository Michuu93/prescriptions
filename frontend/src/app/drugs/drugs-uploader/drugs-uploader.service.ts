import {Injectable} from '@angular/core';
import {FileItem, FileUploader, ParsedResponseHeaders} from 'ng2-file-upload';
import {MatSnackBar} from "@angular/material";

@Injectable()
export class DrugsUploaderService {
    uploader: FileUploader;

    constructor(private snackBar: MatSnackBar) {
        this.uploader = new FileUploader({url: '/api/drugs', disableMultipart: true, autoUpload: true});
        this.uploader.onSuccessItem = (item, response, status, headers) => this.onSuccessItem(item, response, status, headers);
        this.uploader.onErrorItem = (item, response, status, headers) => this.onErrorItem(item, response, status, headers);
    }

    onSuccessItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
        let data = JSON.parse(response);
        this.snackBar.open('Successfully upload ' + data.active + ' drugs!');
    }

    onErrorItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): any {
        this.snackBar.open('Cannot upload drugs. Error code ' + status);
    }
}