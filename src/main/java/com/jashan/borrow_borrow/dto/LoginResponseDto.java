package com.jashan.borrow_borrow.dto;

public record LoginResponseDto(
        String jwt,
        Long user_id
) {
}
