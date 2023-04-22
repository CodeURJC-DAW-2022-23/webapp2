import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';

@Component({
  selector: 'asso-events',
  templateUrl: './events.component.html',
  styleUrls: ['../../../css/boostrap.css', './events.component.css']
})

export class Events {
  events: Events[];

  constructor(private assoService: AsoService){}

  ngOnInit() {
    this.assoService.getAssoById(1).subscribe((reponse)=>{ // cambiar getAssoByID por getMyAsso
    
    });
  }
}