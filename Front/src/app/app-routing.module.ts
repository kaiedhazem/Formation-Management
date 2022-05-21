import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DomaineComponent } from './domaine/domaine.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ProfilComponent } from './profil/profil.component';
import { UserComponent } from './user/user.component';
import { OrganismeComponent } from './organisme/organisme.component';
import { PaysComponent } from './pays/pays.component';
import { FormateurComponent } from './formateur/formateur.component';
import { ParticipantComponent } from './participant/participant.component';
import { FormationComponent } from './formation/formation.component';
import { SessionComponent } from './session/session.component';
import { DashboardComponent } from './dashboard/dashboard.component';

const routes: Routes = [
  { path: "" , component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, 
      children:[
        { path: 'domaine', component: DomaineComponent },
        { path: 'user', component: UserComponent },
        { path: 'organisme', component: OrganismeComponent },
        { path: 'profil', component: ProfilComponent },
        { path: 'pays' , component: PaysComponent},
        { path: 'formateur' , component: FormateurComponent},
        { path: 'participant' , component: ParticipantComponent},
        { path: 'formation' , component: FormationComponent},
        { path: 'session' , component: SessionComponent},
        { path: 'dashboard' , component: DashboardComponent}
      ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
