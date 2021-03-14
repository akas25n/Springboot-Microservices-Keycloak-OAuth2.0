package com.keycloak.remoteuserstorageprovider;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;
}
