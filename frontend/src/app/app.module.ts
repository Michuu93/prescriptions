import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {RootComponent} from './root/root.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatPaginatorModule, MatProgressSpinnerModule, MatTableModule,
    MatToolbarModule
} from "@angular/material";
import {HeaderComponent} from './header/header.component';
import {HttpClientModule} from "@angular/common/http";
import { DrugListComponent } from './drugs/drug-list/drug-list.component';
import { DrugsComponent } from './drugs/drugs.component';

@NgModule({
    declarations: [
        RootComponent,
        HeaderComponent,
        DrugListComponent,
        DrugsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatPaginatorModule,
        MatFormFieldModule,
        HttpClientModule,
        MatTableModule,
        MatProgressSpinnerModule
    ],
    providers: [],
    bootstrap: [RootComponent]
})
export class AppModule {
}
