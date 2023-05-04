import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FirstAccessGuard } from '../../shared/guards/first-access.guard';
import { LoginComponent } from './views/login-page/login-page.component';

const routes: Routes = [
  { 
    path: '', 
    component: LoginComponent,
    canActivate: [ FirstAccessGuard ],
    data: { isLogin: true }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { 



}
