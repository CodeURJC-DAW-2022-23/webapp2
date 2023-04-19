import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from './user/user.component';
import { HeaderComponent } from './home/header/header.component';
import { EditMyUserComponent } from './user/edit-my-user/edit-my-user.component';
import { MyUserFavsComponent } from './user/my-user-favs/my-user-favs.component'

const routes: Routes = [
  {path:'', component: HeaderComponent},
  {path:'myUser', component: UserComponent,
  children: [
    { path: '', component: EditMyUserComponent }, // url: about/
    { path: 'favorites', component: MyUserFavsComponent } // url: about/item
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
