import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RouterModule } from '@angular/router';
import { OverlayModule } from '@angular/cdk/overlay';
import { MainLayoutComponent } from './components/main-layout/main-layout.component';
import { OverlaySpinnerComponent } from './components/overlay-spinner/overlay-spinner.component';

import { AvatarModule } from 'primeng-lts/avatar';
import { ButtonModule } from 'primeng-lts/button';
import { CardModule } from 'primeng-lts/card';
import { CheckboxModule } from 'primeng-lts/checkbox';
import { DropdownModule } from 'primeng-lts/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { MenuModule } from 'primeng-lts/menu';
import { OverlayPanelModule } from 'primeng-lts/overlaypanel';
import { PasswordModule } from 'primeng-lts/password';
import { ProgressSpinnerModule } from 'primeng-lts/progressspinner';
import { RippleModule } from 'primeng/ripple';
import { ToastModule } from 'primeng-lts/toast';
import { TooltipModule } from 'primeng-lts/tooltip';

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

    AvatarModule,
    ButtonModule,
    CardModule,
    CheckboxModule,
    DropdownModule,
    InputTextModule,
    MenuModule,
    OverlayPanelModule,
    PasswordModule,
    ProgressSpinnerModule,
    RippleModule,
    ToastModule,
    TooltipModule,
  ],
  exports: [
    CommonModule,
    FlexLayoutModule,
    FormsModule,
    OverlayModule,
    ReactiveFormsModule,
    TranslateModule,

    AvatarModule,
    ButtonModule,
    CardModule,
    CheckboxModule,
    DropdownModule,
    InputTextModule,
    MenuModule,
    OverlayPanelModule,
    PasswordModule,
    ProgressSpinnerModule,
    RippleModule,
    ToastModule,
    TooltipModule,

    MainLayoutComponent,
    OverlaySpinnerComponent,
  ],
  providers: [ 
    DatePipe,
  ],
})

export class SharedModule { }
