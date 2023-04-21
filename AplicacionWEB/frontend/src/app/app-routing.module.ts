import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
// import { UserComponent } from './user/user.component';
// import { HomeComponent } from './home/home.component';
// import { EditMyUserComponent } from './user/edit-my-user/edit-my-user.component';
// import { MyUserFavsComponent } from './user/my-user-favs/my-user-favs.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';

import { Comment } from './details/comments/comments.component';
import { EventsComponent } from './admin/events/events.component';


const routes: Routes = [
  // {path:'', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'events', component: EventsComponent},
  { path: 'comments', component: Comment }
  // children: [
  //     { path: 'editevents', component: EditEventsComponent },
  // ]
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