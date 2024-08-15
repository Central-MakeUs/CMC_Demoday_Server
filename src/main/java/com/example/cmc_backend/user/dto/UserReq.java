package com.example.cmc_backend.user.dto;

import org.jetbrains.annotations.NotNull;

import lombok.*;

public class UserReq {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginUserInfo {
        @NotNull("유저 이름은 필수값입니다.")
        private String username;
        @NotNull("전화번호는 필수값입니다.")
        private String phoneNumber;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SignupUser {
        private String username;
        private String phone;
    }
}
