package com.userwebservice.controllers;

import com.userwebservice.response.ResponseUser;
import com.userwebservice.response.VerifyPasswordResponse;
import com.userwebservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    public ResponseUser findUserByUserName(@PathVariable String email){
        return userService.getUserDetails(email);
    }

    @PostMapping("/{username}/verify-password")
    public VerifyPasswordResponse verifyPassword(@PathVariable String userName, @RequestBody String password){
        VerifyPasswordResponse verifyPasswordResponse = new VerifyPasswordResponse(false);

        ResponseUser user = userService.getUserDetails(userName, password);
        if (user != null){
            verifyPasswordResponse.setResult(true);
        }
        return verifyPasswordResponse;
    }
}
