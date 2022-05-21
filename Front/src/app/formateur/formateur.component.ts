import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { FormateurService } from '../service/formateur.service';
import { OrganismeService } from '../service/organisme.service';

@Component({
  selector: 'app-formateur',
  templateUrl: './formateur.component.html',
  styleUrls: ['./formateur.component.css']
})
export class FormateurComponent implements OnInit {

  displayedColumns: string[] = ['Type', 'Nom', 'Prenom', 'Email','Organisme', 'Telephone', 'Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutFormateurForm !: FormGroup;
  modifierFormateurForm !: FormGroup;
  formateurs:any;
  objectToEdit: any;
  idSupp: any;
  organismes:any;
  orga:any;
 
  constructor(public dialog: MatDialog, private formBuilder : FormBuilder, private formateurService : FormateurService,
    private _snackBar: MatSnackBar, private router: Router, private organismeService: OrganismeService) { }

  ngOnInit(): void {
    this.afficherFormateurs();
    this.getOrganismes();
    this.ajoutFormateurForm = this.formBuilder.group(
      {
       nom : ['', Validators.required],
       prenom : ['', Validators.required],
       email : ['', Validators.required] ,
       tel : ['', Validators.required] ,
       type : ['', Validators.required] ,
       organisme : ['', Validators.required] 
      })
  }

  openDialog(mymodal: any, row: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    if(row){
      this.objectToEdit = row;
      console.log(this.objectToEdit);
      this.orga = this.objectToEdit.organisme.libelle;
      console.log(this.orga);
      this.modifierFormateurForm = this.formBuilder.group(
        {
          id: [ this.objectToEdit.id ],
          nom : [this.objectToEdit.nom , Validators.required],
          prenom : [this.objectToEdit.prenom , Validators.required], 
          email : [this.objectToEdit.email , Validators.required] ,
          tel : [this.objectToEdit.tel , Validators.required],
          type : [this.objectToEdit.type , Validators.required],
          organisme : [this.objectToEdit.organisme , Validators.required] 
        })
        
    }
  }

  openSuppDialog(mymodal: any, id: any) {
    const dialogRef = this.dialog.open(mymodal, {
      width: '30%'
    });
    this.idSupp = id;
  }

  afficherFormateurs():void {
    this.formateurService.getFormateurs()
      .subscribe({
        next:(res)=>{
          this.dataSource = new MatTableDataSource(res);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort=this.sort;
          console.log(this.dataSource);
        },
        error:(err)=>{
          this.openSnackBar("Erreur d'affichage de formateurs!!", "OOPS");
          console.log(err);
        }
      })
  }

  ajouterFormateur(): void {
    console.log(this.ajoutFormateurForm.value);
    if(this.ajoutFormateurForm.valid)
    {
      this.formateurService.addFormateur(this.ajoutFormateurForm.value)
      .subscribe({
        next:(res)=>{
          this.ajoutFormateurForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Formateur ajouté avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.ajoutFormateurForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur d'ajout de formateur!!", "OOPS");
        }
      })
    }
  }

  modifierFormateur() {
    console.log(this.objectToEdit);
    console.log(this.modifierFormateurForm.value);
    if(this.modifierFormateurForm.valid)
    {
      this.formateurService.updateFormateur(this.modifierFormateurForm.value)
      .subscribe({
        next:(res)=>{
          this.modifierFormateurForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("formateur modifié avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.modifierFormateurForm.reset();
          this.dialog.closeAll();
          this.openSnackBar("Erreur de modification de formateur!!", "OOPS");
        }
      })
    }
  }

  supprimerFormateur(){
    this.formateurService.deleteFormateur(this.idSupp)
    .subscribe({
      next:(res)=>{
        this.dialog.closeAll();
        this.openSnackBar("Formateur supprimé avec succés!!", "Ok");
        this.ngOnInit();
      },
      error:()=>{
        this.dialog.closeAll();
        this.openSnackBar("Erreur de suppression de formateur!!", "OOPS");
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


}
