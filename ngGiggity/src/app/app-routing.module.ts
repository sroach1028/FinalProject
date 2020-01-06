import { AdminComponent } from './components/admin/admin.component';
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
  { path: 'users/username/:username', component: UserComponent },
  { path: 'login', component: LoginComponent, runGuardsAndResolvers: 'always' },
  { path: 'register', component: RegisterComponent, runGuardsAndResolvers: 'always' },
  { path: 'post', component: PostComponent, runGuardsAndResolvers: 'always' },
  { path: 'search', component: SearchResultsComponent, runGuardsAndResolvers: 'always'},
  { path: 'profile', component: AltProfileComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'bookings', component: BookingsComponent },
  { path: 'search/:skillName', component: SearchResultsComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true, onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
