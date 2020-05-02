import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrintOneQRComponent } from './print-one-qr.component';

describe('PrintOneQRComponent', () => {
  let component: PrintOneQRComponent;
  let fixture: ComponentFixture<PrintOneQRComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrintOneQRComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrintOneQRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
