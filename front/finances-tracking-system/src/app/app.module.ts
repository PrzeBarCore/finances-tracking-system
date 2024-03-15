import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReceiptModule } from './receipt/receipt.module';
import { CategoryModule } from './category/category.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import {
  FontAwesomeModule,
  FaIconLibrary,
} from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ProductModule } from './product/product.module';
import { SearchModule } from './search/search.module';
import { AccountModule } from './account/account.module';
@NgModule({
  declarations: [AppComponent, MenuComponent],
  imports: [
    AccountModule,
    ReceiptModule,
    CategoryModule,
    ProductModule,
    SearchModule,
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NoopAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas, far);
  }
}
