package Keycloak_Auth_Service.Keycloak_auth_service.service.impl;

import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import Keycloak_Auth_Service.Keycloak_auth_service.repository.NewUserRepository;
import Keycloak_Auth_Service.Keycloak_auth_service.service.RoleService;
import Keycloak_Auth_Service.Keycloak_auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final UserService userService;
    private final NewUserRepository newUserRepository;



    @Value("${app.keycloak.realm}")
    private String realm;
    private final Keycloak keycloak;


    @Override
    public void assignRole(String userId, String roleName) {
        UserResource user = userService.getUser(userId);
        RolesResource rolesResource = getRolesResource();
        RoleRepresentation representation = rolesResource.get(roleName).toRepresentation();
        user.roles().realmLevel().add(Collections.singletonList(representation));

        //NewUserRecord users = newUserRepository.findNewUserByUserId(userId)



        NewUserRecord users = newUserRepository.findNewUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve userId from Keycloak."));
        users.setRole(roleName);
        newUserRepository.save(users);

    }

    private RolesResource getRolesResource() {
        return keycloak.realm(realm).roles();
    }





















    /*@Override
    public void assignRoles(String userId, List<Role> roles) {
        UserResource user = userService.getUser(userId);
        RolesResource rolesResource = getRolesResource();

        // Loop through the roles and assign them to the user
        for (Role role : roles) {
            RoleRepresentation representation = rolesResource.get(role.name()).toRepresentation();
            user.roles().realmLevel().add(Collections.singletonList(representation));
        }

        // Update the roles in the database if needed (optional)
        NewUserRecord users = newUserRepository.findNewUserByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Failed to retrieve userId from Keycloak."));

        // Update the roles field in the user record (optional)
        users.setRoles(roles.stream().map(Role::name).collect(Collectors.toList()));
        newUserRepository.save(users);
    }*/




}
