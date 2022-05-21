import { Component } from '@angular/core';
import { TokenStorageService } from './service/token-storage.service';
import { LoginComponent } from './login/login.component';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'testfront';

  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  username?: string;

  constructor(private tokenStorageService: TokenStorageService,
    private login:LoginComponent,
    private router:Router) { }
  ngOnInit(): void {
    
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    console.log(this.isLoggedIn)
    if (this.isLoggedIn) {
      this.router.navigate(['home/dashboard']); 
        } else {
          this.router.navigate(['login']);
    }
  }
  
}
