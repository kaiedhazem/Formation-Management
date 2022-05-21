import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { OrganismeService } from '../service/organisme.service';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-organisme',
  templateUrl: './organisme.component.html',
  styleUrls: ['./organisme.component.css']
})
export class OrganismeComponent implements OnInit {
  displayedColumns: string[] = ['ID', 'Libelle', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutOrganismeForm !: FormGroup;
  modifierOrganismeForm !: FormGroup;
  organismes:any;
  objectToEdit: any;
  idSupp: any;
  constructor(private tokenStorageService: TokenStorageService,
    public dialog: MatDialog, private formBuilder : FormBuilder, private organismeService : OrganismeService,
    private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.afficherOrganisme();
    this.ajoutOrganismeForm = this.formBuilder.group(
      {
       libelle : ['', Validators.required] 
      })
  }
  openDialog(mymodal: any, row: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    if(row){
      this.objectToEdit = row;
      console.log(this.objectToEdit);
      this.modifierOrganismeForm = this.formBuilder.group(
        {
          id: [ this.objectToEdit.id ],
          libelle : [this.objectToEdit.libelle , Validators.required] 
        })
        
    }
  }

  openSuppDialog(mymodal: any, id: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    this.idSupp = id;
  }

  afficherOrganisme():void {
    this.organismeService.getOrganisme()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de organismes!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouterOrganisme(): void {
    console.log(this.ajoutOrganismeForm.value);
    if(this.ajoutOrganismeForm.valid)
    {
      this.organismeService.addOrganisme(this.ajoutOrganismeForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutOrganismeForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Organisme ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutOrganismeForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout d'organisme !!", "OOPS");
        }
      })
    }
  }

  modifierOrganisme() {
    console.log(this.objectToEdit);
    if(this.modifierOrganismeForm.valid)
    {
      this.organismeService.updateOrganisme(this.modifierOrganismeForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierOrganismeForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Organisme modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierOrganismeForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification d'organisme !!", "OOPS");
        }
      })
    }
  }

  supprimerOrganisme(){
    this.organismeService.deleteOrganisme(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Oragnisme supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression d'organisme !!", "OOPS");
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
