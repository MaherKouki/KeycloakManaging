package Keycloak_Auth_Service.Keycloak_auth_service.api;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coach")
public class CoachController {


    @GetMapping("coach-role")
    @PreAuthorize("hasRole('COACH')")
    public ResponseEntity<String> test() {
        System.out.println("test");
        return ResponseEntity.status(HttpStatus.OK).body("Coaaaaaaach");
    }

}
