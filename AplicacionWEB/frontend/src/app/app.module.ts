import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HttpHandler } from '@angular/common/http';


import { AppComponent } from './app.component';
import { FooterComponent } from './home/footer/footer.component';
import { HeaderComponent } from './home/header/header.component';
import { SearchComponent } from './home/search/search.component';

import { CardComponent } from './home/card/card.component';
import { LoginComponent } from './login/login.component';

import { CommentsComponent } from './details/comments/comments.component';
import { EditAsso } from './asso/edit/edit-aso-form.component';
import { AssoComponent } from './asso/asso.component';
import { AssoNewEvent } from './asso/new_event/new-event.component';
import { Events } from './asso/events/events.component';
import { MoreinfoComponent } from './details/moreinfo/moreinfo.component';
import { MoreinfoheroComponent } from './details/moreinfohero/moreinfohero.component';
import { LikesComponent } from './details/likes/likes.component';
import { FavoritesComponent } from './details/addfavorites/addfavorites.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { UserComponent } from './user/user.component';
import { MyUserFavsComponent } from './user/my-user-favs/my-user-favs.component';
import { HomeComponent } from './home/home.component'
import { EditMyUserComponent } from './user/edit-my-user/edit-my-user.component';
import { AppRoutingModule } from './app-routing.module';
import { AdminComponent } from './admin/admin.component';
import { EventsComponent } from './admin/events/events.component';
import { AsosComponent } from './admin/asos/asos.component';
import { UsersComponent } from './admin/users/users.component';
import { EditeventComponent } from './admin/events/editevent/editevent.component';
import { EditasoComponent } from './admin/asos/editaso/editaso.component';
import { EdituserComponent } from './admin/users/edituser/edituser.component';
import { CommentComponent } from './details/comment/comment.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    SearchComponent,
    CardComponent,
    LoginComponent,
    CommentsComponent,
    MoreinfoComponent,
    MyUserFavsComponent,
    EditMyUserComponent,
    MoreinfoheroComponent,
    LikesComponent,
    FavoritesComponent,
    UserComponent,
    AdminComponent,
    EventsComponent,
    AsosComponent,
    UsersComponent,
    EditeventComponent,
    EditasoComponent,
    EdituserComponent,
    HomeComponent,
    AssoComponent,
    EditAsso,
    AssoNewEvent,
    Events,
    CommentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
