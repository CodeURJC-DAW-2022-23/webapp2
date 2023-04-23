import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AsoService } from 'src/app/services/aso.service';
import { Aso } from 'src/app/models/aso.model';

@Component({
  selector: 'app-asos',
  templateUrl: './asos.component.html',
  styleUrls: ['./asos.component.css']
})
export class AsosComponent {
  id?: number;
  name?: string;
  description?: string;
  faculty?: string;
  campus?: string;
  asos: Aso[];

  constructor(private router: Router, private asoService: AsoService) {
    this.asoService.asoList().subscribe((response) => {
      this.asos = response;
    })
   }

  modify(id: number) {
     this.router.navigate(['/admin/editaso/', id]);
  }
  deleteAso(id: number) {
    this.asoService.deleteAso(id);
  }
}
