import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyTechnicianComponent } from './modify-technician.component';

describe('ModifyTechnicianComponent', () => {
  let component: ModifyTechnicianComponent;
  let fixture: ComponentFixture<ModifyTechnicianComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyTechnicianComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyTechnicianComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
