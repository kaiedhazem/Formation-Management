import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { DomaineService } from '../service/domaine.service';
import { TokenStorageService } from '../service/token-storage.service';


@Component({
  selector: 'app-domaine',
  templateUrl: './domaine.component.html',
  styleUrls: ['./domaine.component.css']
})
export class DomaineComponent implements OnInit {
  user:any;
  Currentuser:any;
  role:string;
  displayedColumns: string[] = ['ID', 'Libelle', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutDomaineForm !: FormGroup;
  modifierDomaineForm !: FormGroup;
  domaines:any;
  objectToEdit: any;
  idSupp: any;
  
  constructor(private tokenStorageService: TokenStorageService,
    public dialog: MatDialog, private formBuilder : FormBuilder, private domaineService : DomaineService,
    private _snackBar: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.afficherDomaines();
    this.ajoutDomaineForm = this.formBuilder.group(
      {
       libelle : ['', Validators.required] 
      }),
      this.user = window.sessionStorage.getItem('auth-user');
      this.Currentuser =JSON.parse(this.user);
      this.role= this.Currentuser.roles[0];
      console.log(this.Currentuser.roles[0]);
  }

  openDialog(mymodal: any, row: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    if(row){
      this.objectToEdit = row;
      console.log(this.objectToEdit);
      this.modifierDomaineForm = this.formBuilder.group(
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

  afficherDomaines():void {
    this.domaineService.getDomaines()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de domaines!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouterDomaine(): void {
    console.log(this.ajoutDomaineForm.value);
    if(this.ajoutDomaineForm.valid)
    {
      this.domaineService.addDomaines(this.ajoutDomaineForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutDomaineForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Domaine ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutDomaineForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de domaine!!", "OOPS");
        }
      })
    }
  }

  modifierDomaine() {
    console.log(this.objectToEdit);
    if(this.modifierDomaineForm.valid)
    {
      this.domaineService.updateDomaines(this.modifierDomaineForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierDomaineForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Domaine modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierDomaineForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de domaine!!", "OOPS");
        }
      })
    }
  }

  supprimerDomaine(){
    this.domaineService.deleteDomaines(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Domaine supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de domaine!!", "OOPS");
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
