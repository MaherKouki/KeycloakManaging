package Keycloak_Auth_Service.Keycloak_auth_service.Model;
/*
public record NewUserRecord(
        String username,
        String password,
        String firstName,
        String lastName
) {
}*/


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.resource.UsersResource;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
public class NewUserRecord {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String username;
    private String password;
    private String firstname;
    private String lastname;

    private int age;
    private int num;


    @CreationTimestamp
    private LocalDateTime createdAt;

    /*@JsonIgnore
    private String userId;*/


    @JsonIgnore
    private String Role;




}




