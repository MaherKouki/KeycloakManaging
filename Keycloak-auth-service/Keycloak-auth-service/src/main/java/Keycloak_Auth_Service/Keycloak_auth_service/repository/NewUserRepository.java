package Keycloak_Auth_Service.Keycloak_auth_service.repository;

import Keycloak_Auth_Service.Keycloak_auth_service.Model.NewUserRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewUserRepository extends JpaRepository<NewUserRecord,Long> {
}
