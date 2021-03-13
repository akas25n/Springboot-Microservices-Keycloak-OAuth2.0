package com.appsdeveloper.resourceserver.controller;

import com.appsdeveloper.resourceserver.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    Environment environment;

    @GetMapping("/status/check")
    public String status(){
        return "Working.....on port : " + environment.getProperty("local.server.port");
    }

    //@Secured("ROLE_developer")
    // deleting the user if the user got developer role or if the delete himself
    @PreAuthorize("hasRole('developer') or #id == #jwt.subject")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return "Deleted user with id - " + id + "and JWT subject - " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt){
        return new UserRest("b6c73f2f-2647-478e-91a8-396649a5aba4", "Sarker", "Akash");
    }
}

