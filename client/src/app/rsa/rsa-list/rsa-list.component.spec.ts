import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RsaListComponent } from './rsa-list.component';

describe('RSAListComponent', () => {
  let component: RsaListComponent;
  let fixture: ComponentFixture<RsaListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RsaListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RsaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
