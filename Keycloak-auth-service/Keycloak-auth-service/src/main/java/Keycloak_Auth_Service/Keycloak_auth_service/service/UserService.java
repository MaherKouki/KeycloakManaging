package Keycloak_Auth_Service.Keycloak_auth_service.service;

import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;

public interface UserService {

    void createUser(NewUserRecord newUserRecord);
    void sendVerificationEmail(String userId);
    //void sendVerificationEmail(Long userId);
    void deleteUser(String UserId);
    void forgotPassword(String userName);

    UserResource getUser(String userId);


}
