import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyRsaComponent } from './modify-rsa.component';

describe('ModifyRSAComponent', () => {
  let component: ModifyRsaComponent;
  let fixture: ComponentFixture<ModifyRsaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyRsaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyRsaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
