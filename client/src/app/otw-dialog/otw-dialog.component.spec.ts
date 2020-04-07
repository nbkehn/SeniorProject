import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OTWDialogComponent } from './otw-dialog.component';

describe('OTWDialogComponent', () => {
  let component: OTWDialogComponent;
  let fixture: ComponentFixture<OTWDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OTWDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OTWDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
