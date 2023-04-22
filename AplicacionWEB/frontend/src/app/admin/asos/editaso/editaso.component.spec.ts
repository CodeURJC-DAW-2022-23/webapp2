import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditasoComponent } from './editaso.component';

describe('EditasoComponent', () => {
  let component: EditasoComponent;
  let fixture: ComponentFixture<EditasoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditasoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditasoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
