import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrintAllQRComponent } from './print-all-qr.component';

describe('PrintAllQRComponent', () => {
  let component: PrintAllQRComponent;
  let fixture: ComponentFixture<PrintAllQRComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrintAllQRComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrintAllQRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
