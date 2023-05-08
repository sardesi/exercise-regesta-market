import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { fader } from '../../animations/animations';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { LangService } from '../../services/lang-service';
import { MenuItem } from 'primeng-lts/api';


@Component({
  selector: 'main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss'],
  animations: [ fader ]
})
export class MainLayoutComponent implements OnInit {

  constructor(
    public authService: AuthService,
    public langService: LangService,
    protected router: Router,
    public userService: UserService
  ) { }

  ngOnInit(): void { }

  prepareOutlet(outlet: RouterOutlet) {
    return outlet.isActivated ? outlet.activatedRoute : '';;
  }

  logout() {
    this.authService.logout();
  }

  showBars() {
    return this.userService.isLogged();
  }

  changeLang(langId: string) {
    this.langService.changeLanguage(langId);
  }

  logoClick(){
    if(this.userService.isLogged()) this.router.navigate(['/product']);
  }

  changeLanguage() {
    this.langService.switchLanguage();
  }

}


