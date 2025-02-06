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


        log.info("New User created successfully");
    }

    @Override
    public void sendVerificationEmail(String userId) {

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();

    }


    private UsersResource getUsersResource() {

        return keycloak.realm(realm).users();
    }
}
