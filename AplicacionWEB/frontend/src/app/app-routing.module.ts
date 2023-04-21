import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
// import { UserComponent } from './user/user.component';
// import { HomeComponent } from './home/home.component';

import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { EventsComponent } from './admin/events/events.component';
import { AsosComponent } from './admin/asos/asos.component';

import { Comment } from './details/comments/comments.component';




const routes: Routes = [
  //{path: 'comments', component: Comment},
  // {path:'', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path: 'admin', component: AdminComponent,
  children: [
    {path: 'events', component: EventsComponent},
    {path: 'asos', component: AsosComponent},
  ]
  }
  
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }