import {NgModule} from '@angular/core';

import {
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule
} from '@angular/material';

@NgModule({
    imports: [
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatPaginatorModule,
        MatFormFieldModule,
        MatTableModule,
        MatProgressSpinnerModule,
        MatMenuModule,
        MatSnackBarModule,
        MatInputModule,
        MatDividerModule,
        MatTabsModule
    ],
    exports: [
        MatToolbarModule,
        MatIconModule,
        MatButtonModule,
        MatCardModule,
        MatPaginatorModule,
        MatFormFieldModule,
        MatTableModule,
        MatProgressSpinnerModule,
        MatMenuModule,
        MatSnackBarModule,
        MatInputModule,
        MatDividerModule,
        MatTabsModule
    ]
})
export class MaterialModule {
}