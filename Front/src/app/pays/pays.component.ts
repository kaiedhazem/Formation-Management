import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PaysService } from '../service/pays.service';
import { TokenStorageService } from '../service/token-storage.service';
@Component({
  selector: 'app-pays',
  templateUrl: './pays.component.html',
  styleUrls: ['./pays.component.css']
})
export class PaysComponent implements OnInit {
  displayedColumns: string[] = ['ID', 'Nom', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutPaysForm !: FormGroup;
  modifierPaysForm !: FormGroup;
  pays:any;
  objectToEdit: any;
  idSupp: any;
  
  constructor(private tokenStorageService: TokenStorageService,
    public dialog: MatDialog, private formBuilder : FormBuilder, private paysService : PaysService,
    private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.afficherPays();
    this.ajoutPaysForm = this.formBuilder.group(
      {
       nom : ['', Validators.required] 
      })
  }

  openDialog(mymodal: any, row: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    if(row){
      this.objectToEdit = row;
      console.log(this.objectToEdit);
      this.modifierPaysForm = this.formBuilder.group(
        {
          id: [ this.objectToEdit.id ],
          nom : [this.objectToEdit.nom , Validators.required] 
        })
        
    }
  }

  openSuppDialog(mymodal: any, id: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    this.idSupp = id;
  }

  afficherPays():void {
    this.paysService.getPays()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de pays!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouterPays(): void {
    console.log(this.ajoutPaysForm.value);
    if(this.ajoutPaysForm.valid)
    {
      this.paysService.addPays(this.ajoutPaysForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutPaysForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Pays ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutPaysForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de pays!!", "OOPS");
        }
      })
    }
  }

  modifierPays() {
    console.log(this.objectToEdit);
    if(this.modifierPaysForm.valid)
    {
      this.paysService.updatePays(this.modifierPaysForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierPaysForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Pays modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierPaysForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de Pays!!", "OOPS");
        }
      })
    }
  }

  supprimerPays(){
    this.paysService.deletePays(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Pays supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de Pays!!", "OOPS");
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
}
