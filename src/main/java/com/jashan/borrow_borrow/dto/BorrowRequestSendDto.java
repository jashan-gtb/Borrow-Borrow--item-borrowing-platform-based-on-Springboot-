package com.jashan.borrow_borrow.dto;

import java.time.LocalDateTime;

public record  BorrowRequestSendDto(
        Long sender_id,
        Long item_id,
        LocalDateTime request_time
) {
    public BorrowRequestSendDto{
        if (request_time==null){
            request_time=LocalDateTime.now();
        }
    }
}
