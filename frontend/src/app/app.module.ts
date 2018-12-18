import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {RootComponent} from './root/root.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatIconModule, MatToolbarModule} from "@angular/material";
import {ToolbarComponent} from './toolbar/toolbar.component';
import {HomeComponent} from './home/home.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
    declarations: [
        RootComponent,
        ToolbarComponent,
        HomeComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        HttpClientModule
    ],
    providers: [],
    bootstrap: [RootComponent]
})
export class AppModule {
}
