package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.dto.LoginAndSignupRequestDto;
import com.jashan.borrow_borrow.dto.LoginResponseDto;
import com.jashan.borrow_borrow.dto.SignupResponseDto;
import com.jashan.borrow_borrow.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginAndSignupRequestDto dto){
        return service.login(dto);
    }
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginAndSignupRequestDto dto){
        return service.signup(dto);
    }
}
