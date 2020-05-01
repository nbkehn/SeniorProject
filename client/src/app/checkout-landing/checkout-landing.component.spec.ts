import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckoutLandingComponent } from './checkout-landing.component';

describe('CheckoutLandingComponent', () => {
  let component: CheckoutLandingComponent;
  let fixture: ComponentFixture<CheckoutLandingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckoutLandingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckoutLandingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
