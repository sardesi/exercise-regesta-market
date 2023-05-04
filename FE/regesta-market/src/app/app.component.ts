import { Component } from '@angular/core';
import { EnvironmentService } from './shared/services/environment.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  title = 'regesta-market';

  constructor(
    private environmentService: EnvironmentService,
  ) { }

  public apiUrl = this.environmentService.apiUrl;
  
}
