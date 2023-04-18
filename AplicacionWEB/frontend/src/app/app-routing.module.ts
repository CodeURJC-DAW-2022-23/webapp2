import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { Comment } from './details/comments/comments.component';


@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})

const appRoutes = [
  { path: 'Comment/:idComment', component: Comment }
]
export const routing = RouterModule.forRoot(appRoutes, { anchorScrolling: 'enabled' });
