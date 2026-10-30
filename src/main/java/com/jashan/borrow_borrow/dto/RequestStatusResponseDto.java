package com.jashan.borrow_borrow.dto;

import com.jashan.borrow_borrow.enums.RequestStatus;

public record RequestStatusResponseDto(
        Long requestId,
        RequestStatus requestStatus
) {
}
