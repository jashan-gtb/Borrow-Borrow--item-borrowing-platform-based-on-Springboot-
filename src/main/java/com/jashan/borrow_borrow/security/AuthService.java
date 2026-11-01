package com.jashan.borrow_borrow.security;

import com.jashan.borrow_borrow.dto.LoginAndSignupRequestDto;
import com.jashan.borrow_borrow.dto.LoginResponseDto;
import com.jashan.borrow_borrow.dto.SignupResponseDto;
import com.jashan.borrow_borrow.enums.Role;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public ResponseEntity<LoginResponseDto> login(LoginAndSignupRequestDto dto){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.user_name(),dto.user_password()));

        Users user=(Users)authentication.getPrincipal();
        if (user == null) throw new AssertionError();
        String jwt=authUtil.generateTokken(user);
        return ResponseEntity.ok(new LoginResponseDto(jwt, user.getUserId()));


    }

    public ResponseEntity<SignupResponseDto> signup(LoginAndSignupRequestDto dto){
        Users user= userRepository.findByUserName(dto.user_name()).orElse(null);
        if (user!= null){
            throw new RuntimeException("User already exists");
        }
        user= new Users();
        user.setUserName(dto.user_name());
        user.setUserEmail(dto.user_email());
        user.setUserPassword(encoder.encode(dto.user_password()));
        user.setRole(Role.USER);
        userRepository.save(user);

        return ResponseEntity.ok(new SignupResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getUserEmail()
        ));

    }


}
