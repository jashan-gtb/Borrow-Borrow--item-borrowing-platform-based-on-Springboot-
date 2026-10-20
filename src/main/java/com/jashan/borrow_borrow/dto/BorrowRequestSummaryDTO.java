package com.jashan.borrow_borrow.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BorrowRequestSummaryDTO(
        Long requestId,
        LocalDateTime requestTime,
        com.jashan.borrow_borrow.enums.RequestStatus requestStatus,
        Long senderId,
        String senderName,
        Long receiverId,
        List<ItemDto> items  
) {}
