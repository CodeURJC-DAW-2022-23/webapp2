import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-addfavorites',
  templateUrl: './addfavorites.component.html',
  styleUrls: ['./addfavorites.component.css']
})
export class FavoritesComponent {
  constructor() { }
  ngOnInit() {
    $("#addFavorites").on("click", function () {
      
    });
  }
}
