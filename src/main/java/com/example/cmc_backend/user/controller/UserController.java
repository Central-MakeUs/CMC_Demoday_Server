package com.example.cmc_backend.user.controller;

import com.example.cmc_backend.common.CommonResponse;
import com.example.cmc_backend.common.annotiation.ApiErrorCodeExample;
import com.example.cmc_backend.user.domain.User;
import com.example.cmc_backend.user.dto.UserReq;
import com.example.cmc_backend.user.dto.UserRes;
import com.example.cmc_backend.user.exception.ApplyParticipateErrorCode;
import com.example.cmc_backend.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@Tag(name = "유저", description = "유저 관련 API")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public CommonResponse<UserRes.Token> login(@Valid @RequestBody UserReq.LoginUserInfo loginUserInfo){
        UserRes.Token token = userService.logIn(loginUserInfo);
        return CommonResponse.onSuccess(token);
    }


  /*  @PostMapping("/signup")
    public CommonResponse<UserRes.Token> signup(@Valid @RequestBody UserReq.SignupUser signupUser) {
        UserRes.Token token = userService.signUp(signupUser);

        return CommonResponse.onSuccess(token);
    }*/

    @PostMapping("/apply")
    @ApiErrorCodeExample(ApplyParticipateErrorCode.class)
    @Operation(summary = "참여 신청", description = "참여 신청")
    public CommonResponse<String> applyParticipate(@Valid @RequestBody UserReq.ApplyParticipate applyParticipate) {
        userService.applyParticipate(applyParticipate);
        return CommonResponse.onSuccess("신청 완료");
    }

    /*@GetMapping("/validate")
    @Operation(summary = "유저 토큰 검증", description = "유저 토큰 검증")
    public CommonResponse<String> checkUserToken(@AuthenticationPrincipal User user) {
        return CommonResponse.onSuccess(user.getUsername());
    }*/
}
