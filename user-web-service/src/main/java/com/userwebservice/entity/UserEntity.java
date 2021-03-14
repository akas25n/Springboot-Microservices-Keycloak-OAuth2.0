package com.userwebservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 5313493413859894403L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String emailVerificationToken;
    private String encryptedPassword;
    private boolean emailVerificationStatus;

}
