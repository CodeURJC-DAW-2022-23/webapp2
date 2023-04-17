import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoginComponent } from 'src/app/login/login.component';

@Component({
  selector: 'app-moreinfo',
  templateUrl: './moreinfo.component.html',
  styleUrls: ['./moreinfo.component.css']
})
export class MoreinfoComponent {
  EVENTname = "Evento 1"
  ASOname = "Asociacion 1";
  description = "¡Te invitamos a introducirte en el mundo del código dando los primeros pasos en programación! Aunque estemos en plena revolución digital y vivamos pegados a la tecnología, el mundo del código y la programación aún resulta algo desconocido o inaccesible... ¡Cuando es todo lo contrario! Descubre con este plan que programar es, no solo una actividad sencilla, sino también estimulante. ";
  date = "12:00 am";
  time = "16 abril";
  place = "Aulario III";
  campus = "Mostoles";
  reservation = "NO";
  credits = "1 ETCS";

  constructor(private http: HttpClient){}
  login = "http://localhost:4200/login";
  getLogin(){
    return this.http.get<LoginComponent>(this.login)
  }
}
