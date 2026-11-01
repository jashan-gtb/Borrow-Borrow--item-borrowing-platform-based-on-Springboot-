package com.jashan.borrow_borrow.dto;

public record LoginAndSignupRequestDto(
        String user_name,
        String user_email,
        String user_password
) {
}
