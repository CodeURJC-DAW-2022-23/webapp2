import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';

@Component({
  selector: 'edit-asso-form',
  templateUrl: './edit-asso-form.component.html',
  styleUrls: ['../asso.component.css', '../../../css/boostrap.css']
})

export class EditAsso {
  asso: Aso;

  constructor(private assoService: AsoService){}

  ngOnInit() {
    this.assoService.getMyAsso().subscribe((response)=>{ // cambiar getAssoByID por getMyAsso
      this.asso = response;
    });
  }

  editAsso(event : any) {
    event.preventDefault();
    this.assoService.editMyAsso(this.asso).subscribe(response=>{});
  }
}

