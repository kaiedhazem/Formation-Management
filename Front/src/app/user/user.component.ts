import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { UserService } from '../service/users.service';
import { TokenStorageService } from '../service/token-storage.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns: string[] = ['ID', 'Username', 'Email','Actions'];
  dataSource: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  
  ajoutUserForm !: FormGroup;
  modifierUserForm !: FormGroup;
  users:any;
  objectToEdit: any;
  idSupp: any;
  
  constructor(private tokenStorageService: TokenStorageService,
    public dialog: MatDialog, private formBuilder : FormBuilder, private userService : UserService,
    private _snackBar: MatSnackBar, private router: Router) { }


    ngOnInit(): void {
      this.afficherUsers();
      this.ajoutUserForm = this.formBuilder.group(
        {
         username : ['', Validators.required] ,
         email : ['', Validators.required] ,
         password : ['', Validators.required] 
        
        })
    }
  
    openDialog(mymodal: any, row: any) {
      const dialogRef = this.dialog.open(mymodal, {
        width: '30%'
      });
      if(row){
        this.objectToEdit = row;
        console.log(this.objectToEdit);
        this.modifierUserForm = this.formBuilder.group(
          {
            code: [ this.objectToEdit.code ],
            username : [this.objectToEdit.username , Validators.required] ,
            email : [this.objectToEdit.email , Validators.required] ,
            password :[ ] 
          })
          
      }
    }
  
    openSuppDialog(mymodal: any, id: any) {
      const dialogRef = this.dialog.open(mymodal, {
        width: '30%'
      });
      this.idSupp = id;
    }
  
    afficherUsers():void {
      this.userService.getUsers()
        .subscribe({
          next:(res)=>{
            this.dataSource = new MatTableDataSource(res);
            this.dataSource.paginator = this.paginator;
            this.dataSource.sort=this.sort;
            console.log(this.dataSource);
          },
          error:(err)=>{
            this.openSnackBar("Erreur d'affichage des users!!", "OOPS");
            console.log(err);
          }
        })
    }
  
    ajouterUser(): void {
      console.log(this.ajoutUserForm.value);
      if(this.ajoutUserForm.valid)
      {
        this.userService.addUsers(this.ajoutUserForm.value)
        .subscribe({
          next:(res)=>{
            this.ajoutUserForm.reset();
            this.dialog.closeAll();
            this.openSnackBar("User ajouté avec succés!!", "Ok");
            this.ngOnInit();
          },
          error:()=>{
            this.ajoutUserForm.reset();
            this.dialog.closeAll();
            this.openSnackBar("Erreur d'ajout de user!!", "OOPS");
          }
        })
      }
    }
  
    modifierUser() {
      console.log(this.objectToEdit);
      if(this.modifierUserForm.valid)
      {
        this.userService.updateUsers(this.modifierUserForm.value)
        .subscribe({
          next:(res)=>{
            this.modifierUserForm.reset();
            this.dialog.closeAll();
            this.openSnackBar("User modifié avec succés!!", "Ok");
            this.ngOnInit();
          },
          error:()=>{
            this.modifierUserForm.reset();
            this.dialog.closeAll();
            this.openSnackBar("Erreur de modification de user!!", "OOPS");
          }
        })
      }
    }
  
    supprimerUser(){
      this.userService.deleteUsers(this.idSupp)
      .subscribe({
        next:(res)=>{
          this.dialog.closeAll();
          this.openSnackBar("User supprimé avec succés!!", "Ok");
          this.ngOnInit();
        },
        error:()=>{
          this.dialog.closeAll();
          this.openSnackBar("Erreur de suppression de user!!", "OOPS");
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
