package Keycloak_Auth_Service.Keycloak_auth_service.service;

import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;

public interface RoleService {


    void assignRole(String userId , String roleName);



}
