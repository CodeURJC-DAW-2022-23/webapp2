import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { EditMyUserComponent } from './user/edit-my-user/edit-my-user.component';
import { MyUserFavsComponent } from './user/my-user-favs/my-user-favs.component';
import { LoginComponent } from './login/login.component';
import { EventInfoComponent } from './event-info/event-info.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'login', component: LoginComponent},
  {path:'myUser', component: UserComponent,
  children: [
    { path: '', component: EditMyUserComponent }, // url: about/
    { path: 'favorites', component: MyUserFavsComponent } // url: about/item
  ]
  },
  {path: 'infoEvento/:id', component: EventInfoComponent}
  
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
