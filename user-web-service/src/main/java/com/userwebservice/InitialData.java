package com.userwebservice;

import com.userwebservice.entity.UserEntity;
import com.userwebservice.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialData {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public InitialData(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent readyEvent){
        UserEntity userEntity = new UserEntity(
                1L,
                "fhkjahklfa90f",
                "Abdur",
                "Rahim",
                "abdur.rahim@testmail.com",
                bCryptPasswordEncoder.encode("rahim"),
                "",
                false);

        userRepository.save(userEntity);
    }
}
