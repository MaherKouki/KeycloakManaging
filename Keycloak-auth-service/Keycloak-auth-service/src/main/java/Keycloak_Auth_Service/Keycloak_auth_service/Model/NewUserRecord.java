package Keycloak_Auth_Service.Keycloak_auth_service.Model;

public record NewUserRecord(
        String username,
        String password,
        String firstName,
        String lastName
) {
}
