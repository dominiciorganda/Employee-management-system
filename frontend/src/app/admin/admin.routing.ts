import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ImageComponent } from '../image/image.component';
import { DashboardComponent } from "./dashboard/dashboard.component";
import { SkillAreasComponent } from "./skill-areas/skill-areas.component";

const routes: Routes = [
  { path: "", component: DashboardComponent },
  { path: "skill-areas", component: SkillAreasComponent },
  {path:"img", component: ImageComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
