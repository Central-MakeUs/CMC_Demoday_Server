package com.example.cmc_backend.user.convertor;

import static com.example.cmc_backend.user.domain.enums.UserRole.*;

import com.example.cmc_backend.common.entity.enums.Status;
import com.example.cmc_backend.user.domain.User;
import com.example.cmc_backend.user.dto.UserReq;

public class UserConvertor {

    public static User signUpUser(UserReq.SignupUser signupUser, String passwordEncoded) {
        return User.builder()
                .username(signupUser.getUsername())
                .password(passwordEncoded)
				.name(signupUser.getUsername())
                .phoneNumber(signupUser.getPhone())
                .role(ROLE_USER.toString())
                .build();
    }

	public static User applyParticipate(UserReq.ApplyParticipate applyParticipate, String passwordEncoded) {
		return User.builder()
				.username(applyParticipate.getUsername()+"_"+applyParticipate.getPhoneNumber())
				.password(passwordEncoded)
				.name(applyParticipate.getUsername())
				.phoneNumber(applyParticipate.getPhoneNumber())
				.role(ROLE_USER.toString())
				.userClassification(applyParticipate.getUserClassification())
				.participationClassification(applyParticipate.getParticipationClassification())
				.infoChannel(applyParticipate.getInfoChannel())
				.status(Status.ACTIVE)
				.build();
	}
}
