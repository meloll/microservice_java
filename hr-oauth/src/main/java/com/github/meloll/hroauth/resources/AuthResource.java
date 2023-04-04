package com.github.meloll.hroauth.resources;

import com.github.meloll.hroauth.dtos.CredentialsDto;
import com.github.meloll.hroauth.dtos.TokenDto;
import com.github.meloll.hroauth.dtos.UserDto;
import com.github.meloll.hroauth.services.TokenService;
import com.github.meloll.hroauth.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthResource {
    private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

    private final TokenService tokenService;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public AuthResource(TokenService tokenService, UserService userService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/token",
    consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<TokenDto> token(@RequestParam Map<String,String> params) {

        UserDto userDto = userService.findByEmail(params.get("username"));
        boolean isPasswordCorrect = passwordEncoder.matches(params.get("password"), userDto.getPassword() );

        String token = null;
        if(isPasswordCorrect){
            token = tokenService.generateToken(userDto);

            TokenDto tokenDto = TokenDto.builder()
                    .token(token)
                    .userToken(userDto.getUsername())
                    .build();

            return ResponseEntity.ok(tokenDto);

        }
        return ResponseEntity.badRequest().build();
    }
}
