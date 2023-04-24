import { Component } from '@angular/core';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-editaso',
  templateUrl: './editaso.component.html',
  styleUrls: ['./editaso.component.css']
})
export class EditasoComponent {
  aso: Aso;

  constructor(private router: Router, activatedRoute: ActivatedRoute, private asoService: AsoService) {
     const idEvent = activatedRoute.snapshot.params['id'] as number;
    this.load(idEvent);
  }

   load(id: number){
    this.asoService.getAssoById(id).subscribe((response)=>{ 
      this.aso = response;
    });
  }
  ngOnInit() {
    
  }

  editAso(id: number) {
    this.asoService.adminEditAso(id, this.aso).subscribe(response => {
      this.router.navigate(['admin/asos']);
    });
  }
}
