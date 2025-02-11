package Keycloak_Auth_Service.Keycloak_auth_service.repository;

import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewUserRepository extends JpaRepository<NewUserRecord,String> {

    Optional<NewUserRecord> findNewUserByUserId(String username);
}
