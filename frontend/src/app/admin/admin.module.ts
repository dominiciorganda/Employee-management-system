import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { AdminRoutingModule } from "./admin.routing";
import { MaterialModule } from "../material/material.module";
import { SkillAreasComponent } from './skill-areas/skill-areas.component';

@NgModule({
  declarations: [DashboardComponent, SkillAreasComponent],
  imports: [CommonModule, AdminRoutingModule, MaterialModule],
})
export class AdminModule {}
