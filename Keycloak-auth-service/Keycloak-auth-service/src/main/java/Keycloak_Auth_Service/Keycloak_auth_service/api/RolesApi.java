package Keycloak_Auth_Service.Keycloak_auth_service.api;


import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import Keycloak_Auth_Service.Keycloak_auth_service.repository.NewUserRepository;
import Keycloak_Auth_Service.Keycloak_auth_service.service.RoleService;
import Keycloak_Auth_Service.Keycloak_auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RolesApi {

    private final RoleService roleService;


    @PutMapping("/assign/users/{userId}")
    public ResponseEntity<?> assignRole(@PathVariable String userId , @RequestParam String roleName) {
        roleService.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/remove/users/{userId}")
    public ResponseEntity<?> unAssignRole(@PathVariable String userId , @RequestParam String roleName) {
        roleService.deleteRoleFromUser(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
