import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent } from './category/category.component';
import { ItemComponent } from './item/item.component';
import { DescriptionComponent } from './description/description.component';

const routes: Routes = [
  {
    path: 'category/:id/:name',
    component: ItemComponent,
  },

  { path: 'item/:itemid/:itemname', component: DescriptionComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
