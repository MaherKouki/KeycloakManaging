import { Injectable } from '@angular/core';
import Keycloak from 'keycloak-js';


@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

  private _keycloak: Keycloak | undefined;

  constructor() { }

  get keycloak(){
    if (!this._keycloak) {
      this._keycloak = new Keycloak({
        url: 'http://localhost:9082',
        realm : 'spring',
        clientId : 'test-app'
      })
    }
    return this._keycloak;
  }


  /*async init(): Promise<void> {
    try {
      const authenticated = await this.keycloak.init({
        onLoad: 'login-required'
      });
      console.log('Keycloak authentication:', authenticated);
    } catch (error) {
      console.error('Keycloak initialization failed', error);
    }
  }*/


  async init(): Promise<void> {
    try {
      const authenticated = await this.keycloak.init({
        onLoad: 'login-required'
      });
      if (authenticated) {
        await this.keycloak.loadUserProfile();
        console.log('Token after login:', this.keycloak.tokenParsed);
        //console.log('Token after login:', this.keycloak.tokenParsed);

      }
    } catch (error) {
      console.error('Keycloak initialization failed', error);
    }
  }



  async login(){
    await this.keycloak.login();
  }

  get userInfo() : string{
    return this.keycloak?.tokenParsed?.sub as string;
  }

  get isTokenValid() : boolean{
    return !this.keycloak.isTokenExpired();
  }

  get fullName() : string{
    return this.keycloak?.tokenParsed?.['name'] as string;
  }

  logout(){
    return this.keycloak.logout({redirectUri: 'http://localhost:4200'});
  }

  accountManagement(){
    return this.keycloak.accountManagement();
  }

  getRoleFromToken(token: any): boolean {
    return token?.realm_access?.roles?.includes("COACH") ?? false;
  }


   get roles(): string[] {
     return this.keycloak?.tokenParsed?.['realm_access']?.['roles'] || [];
   }


   hasRoleOfCoach(role: string): boolean {
     return this.roles.includes(role.toUpperCase() as string);
   }









}
