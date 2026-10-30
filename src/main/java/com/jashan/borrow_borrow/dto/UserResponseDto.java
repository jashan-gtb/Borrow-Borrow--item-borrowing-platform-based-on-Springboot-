package com.jashan.borrow_borrow.dto;

import com.jashan.borrow_borrow.enums.RequestStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public record UserResponseDto(
        Long user_id,
        String user_name,
        List<ItemDto> items,
        List<BorrowRequestSummaryDTO> sendRequests,
        List<BorrowRequestSummaryDTO> receiveRequests


) {
}

