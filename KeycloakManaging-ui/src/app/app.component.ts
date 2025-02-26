import {Component, OnInit} from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {KeycloakService} from './utils/keycloak/keycloak.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'KeycloakManaging-ui';
  constructor(private keycloakService: KeycloakService, private router: Router) {}





  logout() {
    this.keycloakService.logout();
  }
}

/*export class AppComponent implements OnInit {
  title = 'KeycloakManaging-ui';
  constructor(private keycloakService: KeycloakService, private router: Router) {}

  ngOnInit() {
    const roles = this.keycloakService.roles;

    if (roles.includes('admin')) {
      this.router.navigate(['/admin']);
    } else if (roles.includes('coach')) {
      this.router.navigate(['/coach']);
    } else if (roles.includes('analyst')) {
      this.router.navigate(['/analyst']);
    } else if (roles.includes('player')) {
      this.router.navigate(['/player']);
    } else if (roles.includes('doctor')) {
      this.router.navigate(['/doctor']);
    } else {
      this.router.navigate(['/unauthorized']);
    }
  }
}*/
