import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MarketUser } from 'src/app/shared/model/_interfaces';
import { AuthService } from 'src/app/shared/services/auth.service';
import { LangService } from 'src/app/shared/services/lang-service';
import { LocalStorageService } from 'src/app/shared/services/local-storage.service';
import { ToastService } from 'src/app/shared/services/toast-service';

@Component({
  selector: 'login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginComponent implements OnInit {
  
  hasBeenHere: boolean = false;
  hidePassword: boolean = true;
  loading: boolean = false;
  rememberMe: boolean = false;
  user: MarketUser = { mail: "", password: "" };

  constructor(
    private authService: AuthService,
    private langService: LangService,
    private localStorageService: LocalStorageService,
    private route: ActivatedRoute,
    private router: Router,
    private toastService: ToastService,
  ) { }

  ngOnInit(): void {

    this.hasBeenHere = this.localStorageService.get(LocalStorageService.STORAGE_HAS_BEEN_HERE) ? true : false;

    // TODO-MOCK: Rimuovere
    this.user.mail = "simoneardesi@outlook.it";
    this.user.password = "password";

  }

  doLogin() {

    this.loading = true;
    let authObservable = this.authService.initUser(this.user.mail!, this.user.password!, this.rememberMe);

    authObservable.subscribe(
      user => {
        this.loading = false;
        this.langService.setLanguage(user.language!, false);
        this.router.navigate(['/product']);
      }
    );

  }

}
