import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DomaineService } from '../service/domaine.service';
import { FormateurService } from '../service/formateur.service';
import { FormationService } from '../service/formation.service';
import { OrganismeService } from '../service/organisme.service';
import { ParticipantService } from '../service/participant.service';
import { SessionService } from '../service/session.service';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  displayedColumns: string[] = ['Date debut', 'Date fin', 'Formateur', 'Formation','Lieu', 'nb de participants','Organisme','Participants', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutForm !: FormGroup;
  modifierForm !: FormGroup;
  objectToEdit: any;
  idSupp: any;
  organismes:any;
  formateurs:any;
  formations:any;
  participants:any;
  allParticipants:any;

  constructor(public dialog: MatDialog, private formBuilder : FormBuilder,
    private _snackBar: MatSnackBar, private router: Router, private domaineService: DomaineService,
    private sessionService: SessionService, private formationService: FormationService, private formateurService: FormateurService,
    private organismeService: OrganismeService, private participantService: ParticipantService) { }

  ngOnInit(): void {
    this.afficher();
    this.getOrganismes();
    this.getFormateurs();
    this.getFormations();
    this.getParticipants();
    this.ajoutForm = this.formBuilder.group(
      {
       date_debut : ['', Validators.required],
       date_fin : ['', Validators.required],
       formateur : ['', Validators.required] ,
       formation : ['', Validators.required] ,
       lieu : ['', Validators.required] ,
       nb_participant : ['', Validators.required],
       organisme: ['', Validators.required],
       participants: ['', Validators.required]
      })
  }

  openDialog(mymodal: any, row: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    if(row){
      this.objectToEdit = row;
      console.log(this.objectToEdit);
      this.modifierForm = this.formBuilder.group(
        {
          id: [this.objectToEdit.id],
          date_debut : [this.objectToEdit.date_debut, Validators.required],
          date_fin : [this.objectToEdit.date_fin, Validators.required],
          formateur : [this.objectToEdit.formateur, Validators.required] ,
          formation : [this.objectToEdit.formation, Validators.required] ,
          lieu : [this.objectToEdit.lieu, Validators.required] ,
          nb_participant : [this.objectToEdit.nb_participant, Validators.required],
          organisme: [this.objectToEdit.organisme, Validators.required],
          participants: [this.objectToEdit.participants, Validators.required]
        })
    }
  }

  openSuppDialog(mymodal: any, id: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    this.idSupp = id;
  }

  openAffichDialog(mymodal: any, data:any){
    const dialogRef = this.dialog.open(mymodal, {
      width: '70%'
    });
    this.participants=data;
    console.log(this.participants);
  }

  afficher():void {
    this.sessionService.getSessions()
      .subscribe({
        next:(res)=>{
          console.log(res);
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de sessions!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouter(): void {
    console.log(this.ajoutForm.value);
    if(this.ajoutForm.valid)
    {
      this.sessionService.addSession(this.ajoutForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Session ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de session!!", "OOPS");
        }
      })
    }
  }

  modifier() {
    console.log(this.objectToEdit);
    console.log(this.modifierForm.value);
    if(this.modifierForm.valid)
    {
      this.sessionService.updateSession(this.modifierForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Session modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de session!!", "OOPS");
        }
      })
    }
  }

  supprimer(){
    this.sessionService.deleteSession(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Session supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:(err)=>{
        console.log(err);
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de session!!", "OOPS");
      }
    })
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getOrganismes(){
    this.organismeService.getOrganisme()
    .subscribe({
      next:(res)=>{
        this.organismes = res;
        console.log(this.organismes);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getFormateurs(){
    this.formateurService.getFormateurs()
    .subscribe({
      next:(res)=>{
        this.formateurs = res;
        console.log(this.formateurs);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getFormations(){
    this.formationService.getFormations()
    .subscribe({
      next:(res)=>{
        this.formations = res;
        console.log(this.formations);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getParticipants(){
    this.participantService.getParticipants()
    .subscribe({
      next:(res)=>{
        this.allParticipants = res;
        console.log(this.allParticipants);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }


}
