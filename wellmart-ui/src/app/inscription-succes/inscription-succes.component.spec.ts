import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionSuccesComponent } from './inscription-succes.component';

describe('InscriptionSuccesComponent', () => {
  let component: InscriptionSuccesComponent;
  let fixture: ComponentFixture<InscriptionSuccesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionSuccesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionSuccesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
