import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { FooterComponent } from './home/footer/footer.component';
import { HeaderComponent } from './home/header/header.component';
import { SearchComponent } from './home/search/search.component';
import { CardComponent } from './home/card/card.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component'
@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    SearchComponent,
    CardComponent,
    LoginComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
