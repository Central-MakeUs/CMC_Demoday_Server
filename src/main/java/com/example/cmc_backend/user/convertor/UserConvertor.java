package com.example.cmc_backend.user.convertor;

import static com.example.cmc_backend.user.domain.enums.UserRole.*;

import com.example.cmc_backend.user.domain.User;
import com.example.cmc_backend.user.dto.UserReq;

import java.util.Collections;

public class UserConvertor {

    public static User SignUpUser(UserReq.SignupUser signupUser, String passwordEncoded) {
        return User.builder()
                .username(signupUser.getUsername())
                .password(passwordEncoded)
                .phoneNumber(signupUser.getPhone())
                .role(ROLE_USER.toString())
                .build();
    }
}
