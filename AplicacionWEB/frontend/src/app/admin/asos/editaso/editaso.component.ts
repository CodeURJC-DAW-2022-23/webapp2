import { Component } from '@angular/core';
import { Aso } from 'src/app/models/aso.model';
import { AsoService } from 'src/app/services/aso.service';

@Component({
  selector: 'app-editaso',
  templateUrl: './editaso.component.html',
  styleUrls: ['./editaso.component.css']
})
export class EditasoComponent {
  aso: Aso;

  constructor(private asoService: AsoService){}

  ngOnInit(id: Number) {
    this.asoService.getAssoById(id).subscribe((response)=>{ 
      this.aso = response;
    });
  }
  editAso(aso : any) {
    aso.preventDefault();
    this.asoService.adminEditAso(this.aso).subscribe(response=>{});
  }
}
