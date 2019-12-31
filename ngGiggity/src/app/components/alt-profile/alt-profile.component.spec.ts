import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AltProfileComponent } from './alt-profile.component';

describe('AltProfileComponent', () => {
  let component: AltProfileComponent;
  let fixture: ComponentFixture<AltProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AltProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AltProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
