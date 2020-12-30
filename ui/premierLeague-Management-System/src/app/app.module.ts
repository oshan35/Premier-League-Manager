import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClubsComponent } from './clubs/clubs.component';
import { MatchComponent } from './match/match.component';
import {ClubServices} from './services/club-services.service'


@NgModule({
  declarations: [
    AppComponent,
    ClubsComponent,
    MatchComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    ClubServices,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
