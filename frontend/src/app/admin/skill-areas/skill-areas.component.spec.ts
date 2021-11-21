import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillAreasComponent } from './skill-areas.component';

describe('SkillAreasComponent', () => {
  let component: SkillAreasComponent;
  let fixture: ComponentFixture<SkillAreasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillAreasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillAreasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
