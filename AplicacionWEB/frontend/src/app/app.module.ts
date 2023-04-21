import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { FooterComponent } from './home/footer/footer.component';
import { HeaderComponent } from './home/header/header.component';
import { SearchComponent } from './home/search/search.component';
import { CardComponent } from './home/card/card.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component'
import { AssoComponent } from './asso/asso.component';
import { EditAsso } from './asso/edit/edit-aso-form.component';
import { AssoNewEvent } from './asso/new_event/new-event.component';
import { Events } from './asso/events/events.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    SearchComponent,
    CardComponent,
    LoginComponent,
    AdminComponent,
    AssoComponent,
    EditAsso,
    AssoNewEvent,
    Events
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
