package com.example.cmc_backend.user.service;

import static com.example.cmc_backend.common.exception.errorCode.UserAuthErrorCode.*;
import static com.example.cmc_backend.user.exception.UserAuthErrorCode.*;

import com.example.cmc_backend.common.exception.NotFoundException;
import com.example.cmc_backend.jwt.JwtService;
import com.example.cmc_backend.user.convertor.UserConvertor;
import com.example.cmc_backend.user.domain.User;
import com.example.cmc_backend.user.dto.UserReq;
import com.example.cmc_backend.user.dto.UserRes;
import com.example.cmc_backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserRes.Token logIn(UserReq.LoginUserInfo loginUserInfo){

        User user=userRepository.findByUsername(loginUserInfo.getUsername()+"_"+loginUserInfo.getPhoneNumber()).orElseThrow(() -> new NotFoundException(NOT_EXIST_USER));

        Long userId = user.getId();

        UserRes.GenerateToken generateToken=createToken(userId);

        return new UserRes.Token(userId, generateToken.getAccessToken(), generateToken.getRefreshToken());
    }

    private UserRes.GenerateToken createToken(Long userId) {
        String accessToken=jwtService.createToken(userId);
        String refreshToken=jwtService.createRefreshToken(userId);

        return new UserRes.GenerateToken(accessToken,refreshToken);
    }

    @Override
    @Transactional
    public UserRes.Token signUp(UserReq.SignupUser signupUser) {
        String passwordEncoded=passwordEncoder.encode(signupUser.getPhone());

        User user = UserConvertor.signUpUser(signupUser, passwordEncoded);

        Long userId=userRepository.save(user).getId();

        UserRes.GenerateToken token = createToken(userId);

        return new UserRes.Token(userId,token.getAccessToken(),token.getRefreshToken());
    }

    @Override
    public boolean checkUserId(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean validationPassword(String password) {
        // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }

    @Override
    public boolean validationPhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }

    @Override
    @Transactional
    public void applyParticipate(UserReq.ApplyParticipate applyParticipate) {
        if(userRepository.existsByUsername(applyParticipate.getUsername()+"_"+applyParticipate.getPhoneNumber())){
            throw new NotFoundException(ALREADY_EXIST_USER);
        }
        String passwordEncoded=passwordEncoder.encode(applyParticipate.getPhoneNumber());
        User user = UserConvertor.applyParticipate(applyParticipate, passwordEncoded);
        userRepository.save(user);
    }
}
