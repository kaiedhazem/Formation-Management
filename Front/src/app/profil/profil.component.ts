import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ProfilService } from '../service/profil.service';
import { TokenStorageService } from '../service/token-storage.service';


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

 
  displayedColumns: string[] = ['ID', 'Libelle', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutProfilForm !: FormGroup;
  modifierProfilForm !: FormGroup;
  profils:any;
  objectToEdit: any;
  idSupp: any;
  
  constructor(private tokenStorageService: TokenStorageService,
    public dialog: MatDialog, private formBuilder : FormBuilder, private profilService : ProfilService,
    private _snackBar: MatSnackBar, private router: Router) { }


  ngOnInit(): void {
    this.afficherProfil();
    this.ajoutProfilForm = this.formBuilder.group(
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
      this.modifierProfilForm = this.formBuilder.group(
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

  afficherProfil():void {
    this.profilService.getProfil()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage des profils!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouterProfil(): void {
    console.log(this.ajoutProfilForm.value);
    if(this.ajoutProfilForm.valid)
    {
      this.profilService.addProfil(this.ajoutProfilForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutProfilForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Profil ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutProfilForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de profil!!", "OOPS");
        }
      })
    }
  }

  modifierProfil() {
    console.log(this.objectToEdit);
    if(this.modifierProfilForm.valid)
    {
      this.profilService.updateProfil(this.modifierProfilForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierProfilForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Profil modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierProfilForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de profil!!", "OOPS");
        }
      })
    }
  }

  supprimerProfil(){
    this.profilService.deleteProfil(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Profil supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de profil!!", "OOPS");
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
