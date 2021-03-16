package com.userwebservice.shared;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus;
}
