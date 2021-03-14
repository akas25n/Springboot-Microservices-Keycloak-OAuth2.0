package com.userwebservice.service;

import com.userwebservice.entity.UserEntity;
import com.userwebservice.repository.UserRepository;
import com.userwebservice.response.ResponseUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseUser getUserDetails(String email){
        ResponseUser responseUser = new ResponseUser();
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null){
            return responseUser;
        }
        BeanUtils.copyProperties(userEntity, responseUser);
        return responseUser;
    }

    public ResponseUser getUserDetails(String email, String password){
        ResponseUser responseUser = new ResponseUser();
        UserEntity entity = userRepository.findByEmail(email);
        if (entity == null){
            return responseUser;
        }
        if (bCryptPasswordEncoder.matches(password, entity.getEncryptedPassword())){
            System.out.println("User passwords matches!");
            responseUser = new ResponseUser();
            BeanUtils.copyProperties(entity, responseUser);
        }
        return responseUser;
    }
}
