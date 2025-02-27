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
@RequestMapping("/users")
public class UsersApi {


    private final UserService userService;
    private final NewUserRepository repo;
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRecord newUserRecord) {

        userService.createUser(newUserRecord);
        repo.save(newUserRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    //http://localhost:6700/auth/users/8f260298-7d79-4782-a708-eea15bcd6a85/send-verification-email
    @PutMapping("/{id}/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable String id) {

        userService.sendVerificationEmail(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    //http://localhost:6700/auth/users/forgot-password?username=koukimaher386@gmail.com
    @PutMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String username) {
        userService.forgotPassword(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    @GetMapping("/roles/{id}")
    public ResponseEntity<?> getUserRoles(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserRoles(id));
    }


    /*@PutMapping("/assign/users/{userId}")
    public ResponseEntity<?> assignRole(@PathVariable String userId , @RequestParam String roleName) {
        roleService.assignRole(userId, roleName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }*/








    /*@PutMapping("/{id}/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable Long id) {

        userService.sendVerificationEmail(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }*/



}
