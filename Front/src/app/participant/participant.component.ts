import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { OrganismeService } from '../service/organisme.service';
import { ParticipantService } from '../service/participant.service';
import { PaysService } from '../service/pays.service';
import { ProfilService } from '../service/profil.service';

@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.css']
})
export class ParticipantComponent implements OnInit {


  displayedColumns: string[] = ['Type', 'Nom', 'Prenom', 'Email','Organisme', 'Telephone', 'Profil', 'Pays', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutForm !: FormGroup;
  modifierForm !: FormGroup;
  formateurs:any;
  objectToEdit: any;
  idSupp: any;
  organismes:any;
  pays: any;
  profils: any;
  typeParticipant:any;
  theValue = {id: 4, nom: 'Tunisie'};
  
  constructor(public dialog: MatDialog, private formBuilder : FormBuilder,
    private _snackBar: MatSnackBar, private router: Router, private organismeService: OrganismeService,
    private paysService: PaysService,private profileService: ProfilService, private participantService: ParticipantService) { }

  ngOnInit(): void {
    this.afficherParticipants();
    this.getOrganismes();
    this.getPays();
    this.getProfils();
    this.typeParticipant = "";
    this.ajoutForm = this.formBuilder.group(
      {
       nom : ['', Validators.required],
       prenom : ['', Validators.required],
       email : ['', Validators.required] ,
       tel : ['', Validators.required] ,
       type : ['', Validators.required] ,
       organisme : ['', Validators.required],
       profil: [ '', Validators.required],
       pays: ['']
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
          id: [ this.objectToEdit.id ],
          nom : [this.objectToEdit.nom , Validators.required],
          prenom : [this.objectToEdit.prenom , Validators.required], 
          email : [this.objectToEdit.email , Validators.required] ,
          tel : [this.objectToEdit.tel , Validators.required],
          type : [this.objectToEdit.type , Validators.required],
          organisme : [this.objectToEdit.organisme , Validators.required],
          profil: [ this.objectToEdit.profil , Validators.required],
          pays: [this.objectToEdit.pays ]
        })
        
    }
  }

  openSuppDialog(mymodal: any, id: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    this.idSupp = id;
  }

  afficherParticipants():void {
    this.participantService.getParticipants()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de participants!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouterParticipant(): void {
    console.log(this.ajoutForm.value);
    if(this.typeParticipant=="national")
    {
      this.ajoutForm.controls['pays'].setValue(this.theValue);
    }
    console.log(this.ajoutForm.value);
    if(this.ajoutForm.valid)
    {
      this.participantService.addParticipant(this.ajoutForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Participant ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de participant!!", "OOPS");
        }
      })
    }
  }

  modifierParticipant() {
    console.log(this.objectToEdit);
    console.log(this.modifierForm.value);
    if(this.typeParticipant=="national")
    {
      this.ajoutForm.controls['pays'].setValue(this.theValue);
    }
    if(this.modifierForm.valid)
    {
      this.participantService.updateParticipant(this.modifierForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Participant modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de participant!!", "OOPS");
        }
      })
    }
  }

  supprimerParticipant(){
    this.participantService.deleteParticipant(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Participant supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de participant!!", "OOPS");
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

  getPays(){
    this.paysService.getPays()
    .subscribe({
      next:(res)=>{
        this.pays = res;
        console.log(this.pays);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  getProfils(){
    this.profileService.getProfil()
    .subscribe({
      next:(res)=>{
        this.profils = res;
        console.log(this.profils);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

  radioChange(event: any){
    this.typeParticipant = event.value;
    console.log(event.value);
  }

}
