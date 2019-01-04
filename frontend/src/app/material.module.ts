import {NgModule} from '@angular/core';

import {
    MatButtonModule,
    MatCardModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule,
    MatDividerModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatMenuModule, MatNativeDateModule,
    MatPaginatorModule,
    MatProgressSpinnerModule, MatSelectModule,
    MatSnackBarModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule
} from '@angular/material';

@NgModule({
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
        MatTabsModule,
        MatSelectModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatDialogModule,
        MatCheckboxModule
    ]
})
export class MaterialModule {
}