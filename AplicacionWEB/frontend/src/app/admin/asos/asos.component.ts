import { Component } from '@angular/core';

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
}