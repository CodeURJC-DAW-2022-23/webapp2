import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './home/footer/footer.component';
import { HeaderComponent } from './home/header/header.component';
import { SearchComponent } from './home/search/search.component';
import { CardComponent } from './home/card/card.component';
import { LoginComponent } from './login/login.component';
import { CommentsComponent } from './details/comments/comments.component';
import { MoreinfoComponent } from './details/moreinfo/moreinfo.component';
import { MoreinfoheroComponent } from './details/moreinfohero/moreinfohero.component';
import { LikesComponent } from './details/likes/likes.component';
import { FavoritesComponent } from './details/addfavorites/addfavorites.component';
import { EdituserComponent } from './edituser/edituser.component';
import { MyAccountComponent } from './my-account/my-account.component'

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
    MoreinfoheroComponent,
    LikesComponent,
    FavoritesComponent,
    EdituserComponent,
    MyAccountComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
