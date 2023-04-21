import { Component } from '@angular/core';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent {
  id?: number;
  name?: string;
  date?: string;
  location?: string;
  ASOname?: string;
}
