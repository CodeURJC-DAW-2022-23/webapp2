import { Component, OnInit } from '@angular/core';
import { AsoService } from 'src/app/services/aso.service';
import { EventService } from 'src/app/services/event.service';
import { Event } from 'src/app/models/event.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  page:number=0;
  searchInput:String="";
  monthSelected:String="";
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
  campusSelected:String = "";
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
  asoSelected:String="";
  asoValues=[
    {value: "", content: "Asociación", select: true},
    {value: "All", content: "Todas", select: false}
  ]
  events:Event[] | undefined;
  moreEvents:Boolean=true;

  constructor(private asoService:AsoService, private eventService:EventService){}

  ngOnInit(){
    this.asoService.asoList().subscribe(
      asos=>{for(let aso of asos){
        this.asoValues.push({value: aso.name, content:aso.name, select: false})
      }
    }
    )
    this.search();
  }
  search(){
    this.eventService.eventsByFilters(this.searchInput,this.asoSelected,this.monthSelected,this.campusSelected,this.page).subscribe((response)=>{
      this.events=response.content;
      this.moreEvents=!response.last;
    }
    )
    this.page=0;
    this.moreEvents=true;
    
  }
  loadMore(){
    this.page=this.page+1;
    if(this.moreEvents){
      this.eventService.eventsByFilters(this.searchInput,this.asoSelected,this.monthSelected,this.campusSelected,this.page).subscribe((response)=>{
        this.events=this.events?.concat(response.content);
        this.moreEvents=!response.last;
      }
      )
    }
    
  }
  

}
