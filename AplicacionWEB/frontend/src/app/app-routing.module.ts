import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { EventsComponent } from './admin/events/events.component';
import { AsosComponent } from './admin/asos/asos.component';
import { UsersComponent } from './admin/users/users.component';
import { EditeventComponent } from './admin/events/editevent/editevent.component';
import { EditasoComponent } from './admin/asos/editaso/editaso.component';



const routes: Routes = [
  //{path: 'comments', component: Comment},
  // {path:'', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path: 'admin', component: AdminComponent,
  children: [
    {path: 'events', component: EventsComponent,
      // children: [
      //   { path: 'editevent', component: EditeventComponent }
      // ]
    },
    {path: 'editevent/:id', component: EditeventComponent },
    {path: 'editaso', component: EditasoComponent},

    {path: 'asos', component: AsosComponent },
    {path: 'users', component: UsersComponent},
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