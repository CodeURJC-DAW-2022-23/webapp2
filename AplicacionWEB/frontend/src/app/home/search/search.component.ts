import { Component } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
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

}
