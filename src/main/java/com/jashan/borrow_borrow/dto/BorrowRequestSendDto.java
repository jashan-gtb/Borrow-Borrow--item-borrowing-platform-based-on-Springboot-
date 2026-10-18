package com.jashan.borrow_borrow.dto;

import com.jashan.borrow_borrow.enums.RequestStatus;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.time.LocalDateTime;

public record BorrowRequestSendDto(
        Long user_id,
        Long item_id,
        LocalDateTime request_time
) {
    public BorrowRequestSendDto{
        if (request_time==null){
            request_time=LocalDateTime.now();
        }
    }
}
