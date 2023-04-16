import { Component, OnInit } from '@angular/core';
import * as simpleDatatables from 'simple-datatables';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
    window.addEventListener('DOMContentLoaded', event => {
      const datatablesSimple = document.getElementById('datatablesSimple') as HTMLTableElement;
      if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple);
      }
    });
  }
}


