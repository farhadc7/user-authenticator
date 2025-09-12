package ir.dc.userAuthenticator.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nationalCode;
    private String mobileNumber;
    @Enumerated(EnumType.STRING)
    private UserRole role;


}

