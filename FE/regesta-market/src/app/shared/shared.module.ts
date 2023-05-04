import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RouterModule } from '@angular/router';
import { OverlayModule } from '@angular/cdk/overlay';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { OverlaySpinnerComponent } from './components/overlay-spinner/overlay-spinner.component';
import { ButtonModule } from 'primeng-lts/button';
import { CheckboxModule } from 'primeng-lts/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng-lts/password';
import { RippleModule } from 'primeng/ripple';

@NgModule({
  declarations: [
    MainLayoutComponent,
    OverlaySpinnerComponent,
  ],
  imports: [
    CommonModule,
    FlexLayoutModule,
    FormsModule,
    OverlayModule,
    ReactiveFormsModule,
    RouterModule,
    TranslateModule,

    ButtonModule,
    CheckboxModule,
    InputTextModule,
    PasswordModule,
    RippleModule,
  ],
  exports: [
    CommonModule,
    FlexLayoutModule,
    FormsModule,
    OverlayModule,
    ReactiveFormsModule,
    TranslateModule,

    ButtonModule,
    CheckboxModule,
    InputTextModule,
    PasswordModule,
    RippleModule,

    MainLayoutComponent,
    OverlaySpinnerComponent,
  ],
  providers: [ 
    DatePipe,
  ],
})

export class SharedModule { }
