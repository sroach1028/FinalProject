import { UserComponent } from './components/user/user.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RegisterComponent } from './components/register/register.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { SearchResultsComponent } from './components/search-results/search-results.component';
import { PostComponent } from './components/post/post.component';
import { BookingsComponent } from './components/bookings/bookings.component';
import { AltProfileComponent } from './components/alt-profile/alt-profile.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent, runGuardsAndResolvers: 'always' },
  { path: 'user', component: UserComponent, runGuardsAndResolvers: 'always' },
  { path: 'profile', component: AltProfileComponent },
  { path: 'users/username/:username', component: UserComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'post', component: PostComponent },
  { path: 'search', component: SearchResultsComponent },
  { path: 'bookings', component: BookingsComponent },
  { path: 'search/:skillName', component: SearchResultsComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
