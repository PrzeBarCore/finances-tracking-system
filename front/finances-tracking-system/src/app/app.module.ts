import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReceiptModule } from './receipt/receipt.module';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReceiptModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
