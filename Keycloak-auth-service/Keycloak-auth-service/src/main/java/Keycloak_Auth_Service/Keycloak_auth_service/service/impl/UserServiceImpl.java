package Keycloak_Auth_Service.Keycloak_auth_service.service.impl;

/*
import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import Keycloak_Auth_Service.Keycloak_auth_service.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Value("${app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;

    @Override
    public void createUser(NewUserRecord newUserRecord) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUserRecord.firstName());
        userRepresentation.setLastName(newUserRecord.lastName());
        userRepresentation.setUsername(newUserRecord.username());
        userRepresentation.setEmail(newUserRecord.username());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUserRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);

        log.info("Status Code:{}", response.getStatus());

        if(!Objects.equals(201,response.getStatus())){
            throw new RuntimeException("Status Code:"+response.getStatus());
        }


        log.info("New User created successfully");
    }


    private UsersResource getUsersResource() {

        return keycloak.realm(realm).users();
    }
}*/



import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import Keycloak_Auth_Service.Keycloak_auth_service.repository.NewUserRepository;
import Keycloak_Auth_Service.Keycloak_auth_service.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Value("${app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;
    private final NewUserRepository newUserRepository;

    @Override
    public void createUser(NewUserRecord newUserRecord) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUserRecord.getFirstname());
        userRepresentation.setLastName(newUserRecord.getLastname());
        userRepresentation.setUsername(newUserRecord.getUsername());
        userRepresentation.setEmail(newUserRecord.getUsername());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUserRecord.getPassword());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);

        log.info("Status Code:{}", response.getStatus());

        if(!Objects.equals(201,response.getStatus())){
            throw new RuntimeException("Status Code:"+response.getStatus());
        }

        //

        List<UserRepresentation> users = usersResource.search(newUserRecord.getUsername());

        if (!users.isEmpty()) {
            String keycloakUserId = users.get(0).getId();
            newUserRecord.setUserId(keycloakUserId); //  Set Keycloak userId in entity
        } else {
            throw new RuntimeException("Failed to retrieve userId from Keycloak.");
        }


        log.info("New User created successfully with Keycloak ID: {}", newUserRecord.getUserId());


        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(newUserRecord.getUsername(),true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);
        sendVerificationEmail(userRepresentation1.getId());
        //


        //log.info("New User created successfully");
    }

    @Override
    public void sendVerificationEmail(String userId) {

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }

    @Override
    public void forgotPassword(String userName) {


        UsersResource usersResource = getUsersResource();

        List<UserRepresentation> userRepresentations = usersResource.searchByUsername(userName,true);
        UserRepresentation userRepresentation1 = userRepresentations.get(0);

        UserResource userResource = usersResource.get(userRepresentation1.getId());

        userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));
    }


    public UserResource getUser(String userId){
        UsersResource usersResource = getUsersResource();

        return usersResource.get(userId);
    }




    /*@Override
    public void sendVerificationEmail(Long id) {

        UsersResource usersResource = getUsersResource();
        NewUserRecord userr = newUserRepository.findById(id).orElse(null);


        usersResource.get(userr.getUserId()).sendVerifyEmail();
    }*/





    public UsersResource getUsersResource() {

        return keycloak.realm(realm).users();
    }
}
