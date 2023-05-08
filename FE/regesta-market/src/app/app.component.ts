import { Component } from '@angular/core';
import { EnvironmentService } from './shared/services/environment.service';
import { PrimeNGConfig } from 'primeng-lts/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'regesta-market';

  constructor(
    private environmentService: EnvironmentService,
    private primengConfig: PrimeNGConfig,
  ) { 
    primengConfig.ripple = true;
  }

  public apiUrl = this.environmentService.apiUrl;
  
}
