import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-skill-areas",
  templateUrl: "./skill-areas.component.html",
  styleUrls: ["./skill-areas.component.scss"],
})
export class SkillAreasComponent implements OnInit {
  isEnabled = false;

  constructor() {}

  ngOnInit(): void {}

  enableAddSkillArea() {
    this.isEnabled = true;
  }

  handleSave() {
    this.isEnabled = false;
  }
}
