import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { EditAsso } from './asso/edit/edit-aso-form.component';
import { AssoComponent } from './asso/asso.component';
import { AssoNewEvent } from './asso/new_event/new-event.component';
import { Events } from './asso/events/events.component';
// import { EventsComponent } from './admin/events/events.component';
// import { AsosComponent } from './admin/asos/asos.component';
// import { UsersComponent } from './admin/users/users.component';
// import { EditeventComponent } from './admin/events/editevent/editevent.component';
// import { EditasoComponent } from './admin/asos/editaso/editaso.component';
// import { EdituserComponent } from './admin/users/edituser/edituser.component';
// import { UserComponent } from './user/user.component';
// import { HomeComponent } from './home/home.component';
// import { EditMyUserComponent } from './user/edit-my-user/edit-my-user.component';
// import { MyUserFavsComponent } from './user/my-user-favs/my-user-favs.component';
// import { EventInfoComponent } from './event-info/event-info.component';


const routes: Routes = [

  { path: 'login', component: LoginComponent},
  { path: 'aso', component: AssoComponent,
  children: [
    {path: 'miEspacio', component: EditAsso},
    {path: 'newEvent', component: AssoNewEvent},
    {path: 'eventManagerAso', component: Events}
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