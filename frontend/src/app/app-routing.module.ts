import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DrugsComponent} from "./drugs/drugs.component";

const routes: Routes = [
    {
        path: '',
        redirectTo: "/",
        pathMatch: 'full'
    },
    {
        path: '',
        component: DrugsComponent
    },
    {
        path: '**',
        redirectTo: "/",
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
