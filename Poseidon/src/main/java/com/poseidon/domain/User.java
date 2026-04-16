package com.poseidon.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    @Setter
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Setter
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Setter
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    @Setter
    private String role;
}
