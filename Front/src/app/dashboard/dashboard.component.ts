import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { ChartData, ChartOptions } from 'chart.js';
import { PaysService } from '../service/pays.service';
import { ParticipantService } from '../service/participant.service';
import { FormationService } from '../service/formation.service';
import { FormateurService } from '../service/formateur.service';
import { SessionService } from '../service/session.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  pays:any;
  participants:any;
  formations:any;
  formateurs:any;
  sessions=[];
  test=false;
  FormateursTypes:any;
  participantTypes:any;

  participantData: ChartData<'doughnut'> = {
    labels: ['international', 'national'],
    datasets: [ ],
  };

  chartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Nos participants',
      },
    },
  };

  typeData: ChartData<'pie'> = {
    labels: ['Interne', 'Externe'],
    datasets: [
      /*{ label: 'Laptop', data: [200, 100, 400, 50, 90] },
      { label: 'AC', data: [500, 400, 350, 450, 650] },
      { label: 'Headset', data: [1200, 1500, 1020, 1600, 900] },*/
    ],
  };
  
  chartOptionsType: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Nos formateurs',
      },
    },
  };

  sessionData: ChartData<'pie'> = {
    labels: ["tunis", "ariana"],
    datasets: [
      
    ],
  };

  chartOptionsType1: ChartOptions = {
    responsive: true,
    plugins: {
      title: {
        display: true,
        text: 'Nos participants par session',
      },
    },
  };

  /** Based on the screen size, switch from standard to one column per row */

  

  constructor(private paysService: PaysService,
              private participantService: ParticipantService,
              private formationService : FormationService,
              private formateurService : FormateurService,
              private sessionServices : SessionService         
    ) {}

  ngOnInit(): void {
    this.getFormateurs();
    this.getFormations();
    this.getParticipants();
    this.getPays();
    this.getSessions();
    this.getFormateurTypes();
    this.getParticipantTypes();
    this.test=true;
    console.log(this.test);
  }

  getFormateurs():void {
    this.formateurService.getFormateurs()
    .subscribe({
      next:(res)=>{
        this.formateurs=res;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getFormations():void {
    this.formationService.getFormations()
    .subscribe({
      next:(res)=>{
        this.formations=res;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getPays():void {
    this.paysService.getPays()
    .subscribe({
      next:(res)=>{
        this.pays=res;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getParticipants():void {
    this.participantService.getParticipants()
    .subscribe({
      next:(res)=>{
        this.participants=res;
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getSessions():void {
    this.sessionServices.getSessions()
    .subscribe({
      next:(res)=>{
        let data=[] ;
        let a=[];
        console.log(res);
        for(let i=0; i<res.length ; i++) 
        {
          a[i]= res[i].lieu;
          data[i] = res[i].nb_participant;
        }
        console.log(a);
        console.log(data);
        this.sessionData.labels=a;
        this.sessionData.datasets.push({label: "nb de participants", data :data});
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getFormateurTypes():void {
    this.formateurService.getTypes()
     .subscribe({
      next:(res)=>{
        this.FormateursTypes=[res.interne , res.externe];
        this.typeData.datasets.push({data :this.FormateursTypes});
        console.log(this.FormateursTypes);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getParticipantTypes():void {
    this.participantService.getTypes()
     .subscribe({
      next:(res)=>{
        this.participantTypes=[res.international , res.national];
        this.participantData.datasets.push({data :this.participantTypes});
        console.log(this.participantTypes);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }
}


