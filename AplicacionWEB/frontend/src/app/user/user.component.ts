import { Component } from '@angular/core';
import { faBars, faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  faBars = faBars;
  faUser = faUser;
  username = "Hola"
  showme:boolean=true;
  toogleSidebar(){
    this.showme = !this.showme
  }
}
