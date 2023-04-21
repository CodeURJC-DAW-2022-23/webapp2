import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

@Component({
  selector: 'app-event-info',
  templateUrl: './event-info.component.html',
  styleUrls: ['./event-info.component.css']
})
export class EventInfoComponent {
  constructor(private router: RouterModule, activatedRoute: ActivatedRoute){
    const id = activatedRoute.snapshot.params['id'];
  }
}
