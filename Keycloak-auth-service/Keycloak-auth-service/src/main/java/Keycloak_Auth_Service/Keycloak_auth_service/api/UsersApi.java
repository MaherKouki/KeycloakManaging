package Keycloak_Auth_Service.Keycloak_auth_service.api;


import Keycloak_Auth_Service.Keycloak_auth_service.Model.Coach;
import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import Keycloak_Auth_Service.Keycloak_auth_service.repository.CoachRepo;
import Keycloak_Auth_Service.Keycloak_auth_service.repository.NewUserRepository;
import Keycloak_Auth_Service.Keycloak_auth_service.service.RoleService;
import Keycloak_Auth_Service.Keycloak_auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersApi {


    private final UserService userService;
    private final NewUserRepository repo;
    private final RoleService roleService;
    private final CoachRepo coachRepo;

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMINN')")
    public ResponseEntity<String> test() {
        System.out.println("ADDDDDDMMMIIIINNNN");
        return ResponseEntity.status(HttpStatus.OK).body("Adminnn");
    }




    @PostMapping("postCoach")
    @PreAuthorize("hasRole('ADMINN')")
    public ResponseEntity<?> createC(@RequestBody Coach coach) {

        coachRepo.save(coach);
        System.out.println("coach saved !!!!!!!!!!!!");
        return ResponseEntity.status(HttpStatus.OK).body("Coach saved");
    }



    @PostMapping("create")
    @PreAuthorize("hasRole('ADMINN')")
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
