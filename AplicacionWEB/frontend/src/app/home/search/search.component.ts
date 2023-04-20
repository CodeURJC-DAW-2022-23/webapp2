import { Component } from '@angular/core';
import { AsoService } from 'src/app/services/aso.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  monthSelected:String="All";
  monthsValues = [
    {value: "", content: "Mes", select: true},
    {value: "All", content: "Todos", select: false},
    {value: "ENERO", content: "Enero", select: false},
    {value: "FEBRERO", content: "Febrero", select: false},
    {value: "MARZO", content: "Marzo", select: false},
    {value: "ABRIL", content: "Abril", select: false},
    {value: "MAYO", content: "Mayo", select: false},
    {value: "JUNIO", content: "Junio", select: false},
    {value: "JULIO", content: "Julio", select: false},
    {value: "AGOSTO", content: "Agosto", select: false},
    {value: "SEPTIEMBRE", content: "Septiembre", select: false},
    {value: "OCTUBRE", content: "Octubre", select: false},
    {value: "NOVIEMBRE", content: "Noviembre", select: false},
    {value: "DICIEMBRE", content: "Diciembre", select: false}
  ]
  campusSelected:String = "All";
  campusValues = [
    {value: "", content: "Campus", select: true},
    {value: "All", content: "Todos", select: false},
    {value: "ALCORCON", content: "Alcorcón", select: false},
    {value: "ARANJUEZ", content: "Aranjuez", select: false},
    {value: "FUENLABRADA", content: "Fuenlabrada", select: false},
    {value: "MOSTOLES", content: "Móstoles", select: false},
    {value: "MADRID-VICALVARO", content: "Madrid-Vicalvaro", select: false},
    {value: "MADRID-QUINTANA", content: "Madrid-Quintana", select: false}
  ]
  asoSelected:String="All";
  asoValues=[
    {value: "", content: "Asociación", select: true},
    {value: "All", content: "Todas", select: false}
  ]
  constructor(private asoService:AsoService){}

  ngOnInit(){
    this.asoService.asoList().subscribe(
      asos=>{for(let aso of asos){
        this.asoValues.push({value: aso.name, content:aso.name, select: false})

      }
    }
    )
  }
  

}
