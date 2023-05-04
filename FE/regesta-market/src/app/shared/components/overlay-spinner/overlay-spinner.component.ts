import { Component, OnInit } from '@angular/core';
import { spinnerAnimation } from '../../animations/animations';
import { LoaderService } from '../../services/loader.service';

@Component({
  selector: 'overlay-spinner',
  templateUrl: './overlay-spinner.component.html',
  styleUrls: ['./overlay-spinner.component.scss'],
  animations: [ spinnerAnimation ]
})
export class OverlaySpinnerComponent {

  loading: boolean = false;

  constructor(private loaderService: LoaderService) {

    this.loaderService.isLoading.subscribe((v) => {
      this.loading = v;
    });

  }

}


