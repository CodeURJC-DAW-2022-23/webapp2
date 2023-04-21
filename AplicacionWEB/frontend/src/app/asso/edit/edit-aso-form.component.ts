import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';
import { Aso } from 'src/app/models/aso.model';

@Component({
  selector: 'edit-asso-form',
  templateUrl: './edit-asso-form.component.html',
  styleUrls: ['../asso.component.css', '../../../css/boostrap.css']
})

export class EditAsso {
  asso: Aso;
}