import { Routes } from '@angular/router';
import {AdminComponent} from './admin/admin.component';
import {PlayerComponent} from './player/player.component';
import {CoachComponent} from './coach/coach.component';
import {AnalystComponent} from './analyst/analyst.component';
import {DoctorComponent} from './doctor/doctor.component';
import {UnauthorizedComponent} from './unauthorized/unauthorized.component';
//import {AuthGuard} from './auth/auth-guard.service';

export const routes: Routes = [
  { path: 'admin', component: AdminComponent,/* canActivate: [AuthGuard], data: { roles: ['admin'] }*/ },
  { path: 'player', component: PlayerComponent,/* canActivate: [AuthGuard], data: { roles: ['player'] } */},
  { path: 'coach', component: CoachComponent, /*canActivate: [AuthGuard], data: { roles: ['coach'] } */},
  { path: 'analyst', component: AnalystComponent, /*canActivate: [AuthGuard], data: { roles: ['analyst'] } */},
  { path: 'doctor', component: DoctorComponent, /*canActivate: [AuthGuard], data: { roles: ['doctor'] }*/ },
  { path: 'unauthorized', component: UnauthorizedComponent },
  { path: '**', redirectTo: 'unauthorized' }
];
