import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AsosComponent } from './asos.component';

describe('AsosComponent', () => {
  let component: AsosComponent;
  let fixture: ComponentFixture<AsosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AsosComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AsosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
