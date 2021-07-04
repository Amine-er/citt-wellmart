import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestoDetailComponent } from './resto-detail.component';

describe('RestoDetailComponent', () => {
  let component: RestoDetailComponent;
  let fixture: ComponentFixture<RestoDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestoDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RestoDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
