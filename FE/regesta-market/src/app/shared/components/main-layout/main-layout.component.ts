import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { fader } from '../../animations/animations';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';
import { LangService } from '../../services/lang-service';


@Component({
  selector: 'main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss'],
  animations: [ fader ]
})
export class MainLayoutComponent implements OnInit {

  linguaggi = LangService.LANG_LIST;
  userMenuOpen: boolean = false;

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

  showSidebar() {
    return this.userService.isLogged(); //TODO: aggiungere condizione aggiuntiva per far apparire solo dopo che la pagina principale si è caricata...
  }

  changeLang(langId: string) {
    this.langService.changeLanguage(langId);
  }

  logoClick(){
    if(this.userService.isLogged()) this.router.navigate(['/products']);
  }

}


