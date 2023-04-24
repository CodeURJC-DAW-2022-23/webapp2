import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as simpleDatatables from 'simple-datatables';
import { AsoService } from '../services/aso.service';

@Component({
  selector: 'app-asso',
  templateUrl: './asso.component.html',
  styleUrls: ['./asso.component.css', '../../css/boostrap.css', '../../css/boostrap.css']
})

export class AssoComponent {
  constructor(private router: Router){
  }
  
  goToPage(pageName : string){
    this.router.navigate([`${pageName}`]);
  }
}
