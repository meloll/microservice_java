package com.github.meloll.hroauth.services;

import com.github.meloll.hroauth.dtos.UserDto;
import com.github.meloll.hroauth.feignclient.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserFeignClient userFeignClient;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDto findByEmail(String email){
        UserDto userDto = userFeignClient.findSearch(email).getBody();
        if(userDto == null){
            logger.error("Email not found " + email);
            throw new IllegalArgumentException("Email not Found");
        }
        logger.info("Email found "+ email);
        return userDto;
    }
}
