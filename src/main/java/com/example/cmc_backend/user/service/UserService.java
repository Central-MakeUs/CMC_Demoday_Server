package com.example.cmc_backend.user.service;

import com.example.cmc_backend.user.dto.UserReq;
import com.example.cmc_backend.user.dto.UserRes;

public interface UserService {
    UserRes.Token logIn(UserReq.LoginUserInfo loginUserInfo);

    UserRes.Token signUp(UserReq.SignupUser signupUser);

    boolean checkUserId(String username);

    boolean validationPassword(String password);

    boolean validationPhoneNumber(String phone);

    void applyParticipate(UserReq.ApplyParticipate applyParticipate);
}
