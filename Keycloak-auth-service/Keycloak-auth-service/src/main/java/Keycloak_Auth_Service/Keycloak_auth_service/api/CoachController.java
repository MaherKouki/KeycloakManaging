package Keycloak_Auth_Service.Keycloak_auth_service.api;


import Keycloak_Auth_Service.Keycloak_auth_service.Model.Coach;
import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import Keycloak_Auth_Service.Keycloak_auth_service.repository.CoachRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@AllArgsConstructor
@RequestMapping("/coach")
public class CoachController {


    private final CoachRepo coachRepo;


    @GetMapping("coach-role")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<String> test() {
        System.out.println("beeetttttoniii daviiid");
        return ResponseEntity.status(HttpStatus.OK).body("Coaaaaaaach");
    }


    @PostMapping("post")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<String> post(@RequestBody Coach c) {
        coachRepo.save(c);
        System.out.println("coach saved !!!!!!!!!!!!");
        return ResponseEntity.status(HttpStatus.OK).body("Coach saved");
    }

}
