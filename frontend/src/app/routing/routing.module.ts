import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {DrugsComponent} from "../drugs/drugs.component";
import {PrescriptionsComponent} from "../prescriptions/prescriptions.component";
import {OfficeComponent} from "../office/office.component";
import {PatientsComponent} from "../patients/patients.component";

const routes: Routes = [
    {
        path: '',
        component: PrescriptionsComponent
    },
    {
      path: 'patients',
      component: PatientsComponent
    },
    {
        path: 'drugs',
        component: DrugsComponent
    },
    {
        path: 'office',
        component: OfficeComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class RoutingModule {
}