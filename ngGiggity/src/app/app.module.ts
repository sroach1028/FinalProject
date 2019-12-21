import { UserService } from './services/user.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserComponent } from './components/user/user.component';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { DatePipe } from '@angular/common';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { HomeComponent } from './components/home/home.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { PostComponent } from './components/post/post.component';



@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    NavBarComponent,
    RegisterComponent,
    LoginComponent,
    PageNotFoundComponent,
    HomeComponent,
    SearchResultsComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AuthService, UserService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
