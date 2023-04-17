import { Component } from '@angular/core';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  showme:boolean=true;
  username = "Hola"
  email = "gmail"
  userId = 1;
  toogleSidebar(){
    this.showme = !this.showme
  }
}
