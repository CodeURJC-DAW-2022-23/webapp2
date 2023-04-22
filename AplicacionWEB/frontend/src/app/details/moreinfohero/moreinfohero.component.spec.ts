import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MoreinfoheroComponent } from './moreinfohero.component';

describe('MoreinfoheroComponent', () => {
  let component: MoreinfoheroComponent;
  let fixture: ComponentFixture<MoreinfoheroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MoreinfoheroComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MoreinfoheroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
