import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DomaineService } from '../service/domaine.service';
import { FormationService } from '../service/formation.service';

@Component({
  selector: 'app-formation',
  templateUrl: './formation.component.html',
  styleUrls: ['./formation.component.css']
})
export class FormationComponent implements OnInit {

  displayedColumns: string[] = ['Titre', 'Domaine', 'Type', 'Durée','Année', 'nb de sessions', 'Budget', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutForm !: FormGroup;
  modifierForm !: FormGroup;
  objectToEdit: any;
  idSupp: any;
  domaines:any;
  

  constructor(public dialog: MatDialog, private formBuilder : FormBuilder,
    private _snackBar: MatSnackBar, private router: Router, private domaineService: DomaineService,
    private formationService: FormationService) { }

  ngOnInit(): void {
    this.afficher();
    this.getDomaines();
    this.ajoutForm = this.formBuilder.group(
      {
       titre : ['', Validators.required],
       domaine : ['', Validators.required],
       duree : ['', Validators.required] ,
       annee : ['', Validators.required] ,
       type : ['', Validators.required] ,
       nb_session : ['', Validators.required],
       budget: [ '', Validators.required]
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
          titre: [ this.objectToEdit.titre , Validators.required ],
          domaine : [this.objectToEdit.domaine , Validators.required],
          duree : [this.objectToEdit.duree , Validators.required], 
          annee : [this.objectToEdit.annee , Validators.required] ,
          type : [this.objectToEdit.type , Validators.required],
          nb_session : [this.objectToEdit.nb_session , Validators.required],
          budget: [ this.objectToEdit.budget , Validators.required]
        })
    }
  }

  openSuppDialog(mymodal: any, id: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    this.idSupp = id;
  }

  afficher():void {
    this.formationService.getFormations()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de formations!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouter(): void {
    console.log(this.ajoutForm.value);
    if(this.ajoutForm.valid)
    {
      this.formationService.addFormation(this.ajoutForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Formation ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de formation!!", "OOPS");
        }
      })
    }
  }

  modifier() {
    console.log(this.objectToEdit);
    console.log(this.modifierForm.value);
    if(this.modifierForm.valid)
    {
      this.formationService.updateFormation(this.modifierForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Formation modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de formation!!", "OOPS");
        }
      })
    }
  }

  supprimer(){
    this.formationService.deleteFormation(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Formation supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de formation!!", "OOPS");
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

  getDomaines(){
    this.domaineService.getDomaines()
    .subscribe({
      next:(res)=>{
        this.domaines = res;
        console.log(this.domaines);
      },
      error:(err)=>{
        console.log(err);
      }
    })
  }

}
