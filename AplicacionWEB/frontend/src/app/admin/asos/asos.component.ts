import { Component } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private router: Router) { }

  modify() {
     //this.router.navigate(['/admin/editaso/', this.aso?.id]);
  }
}
