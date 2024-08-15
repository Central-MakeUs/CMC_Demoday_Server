package com.example.cmc_backend.user.dto;

import org.jetbrains.annotations.NotNull;

import com.example.cmc_backend.user.domain.enums.InfoChannel;
import com.example.cmc_backend.user.domain.enums.ParticipationClassification;
import com.example.cmc_backend.user.domain.enums.UserClassification;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
	public static class ApplyParticipate {
        @NotNull("이름은 필수값 입니다.")
        @Schema(description = "이름", example = "홍길동")
        private String username;
        @NotNull("전화번호는 필수값입니다.")
        @Schema(description = "전화번호", example = "01012345678")
        private String phoneNumber;
        @NotNull("유저 구분은 필수값입니다.")
        @Schema(description = "유저 구분", example = "STUDENT")
        private UserClassification userClassification;
        @NotNull("참여 구분은 필수값입니다.")
        @Schema(description = "참여 구분", example = "CLUB")
        private ParticipationClassification participationClassification;
        @NotNull("정보 수신 채널은 필수값입니다.")
        @Schema(description = "정보 수신 채널", example = "ETC")
        private InfoChannel infoChannel;
	}
}
