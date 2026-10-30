package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.dto.BorrowRequestSendDto;
import com.jashan.borrow_borrow.dto.RequestStatusDto;
import com.jashan.borrow_borrow.dto.RequestStatusResponseDto;
import com.jashan.borrow_borrow.enums.RequestStatus;
import com.jashan.borrow_borrow.models.BorrowRequest;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.ItemRepository;
import com.jashan.borrow_borrow.repository.UserRepository;
import com.jashan.borrow_borrow.service.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/borrow-requests")
@RequiredArgsConstructor
public class BorrowRequestController {

    private final BorrowRequestService service;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @PostMapping("/send")
    public BorrowRequestSendDto sendRequest(@RequestBody BorrowRequestSendDto dto ){
        return service.sendRequest(dto);
    }
    @PostMapping("/accept")
    public ResponseEntity<RequestStatusResponseDto> acceptRequest(@RequestBody RequestStatusDto dto){
        Users user= userRepository.findById(dto.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BorrowRequest request= user.getReceiveRequests().stream()
                .filter(borrowRequest -> borrowRequest.getRequest_id().equals(dto.requestId()))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.acceptRequest(request);
        return ResponseEntity.ok(new RequestStatusResponseDto(
                request.getRequest_id(),
                RequestStatus.APPROVED
        ));
    }

    @PostMapping("/reject")
    public ResponseEntity<RequestStatusResponseDto> rejectRequest(@RequestBody RequestStatusDto dto){

        Users user=userRepository.findById(dto.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BorrowRequest request=user.getReceiveRequests().stream()
                .filter(borrowRequest-> borrowRequest.getRequest_id().equals(dto.requestId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.rejectRequest(request);
        return ResponseEntity.ok(new RequestStatusResponseDto(
                request.getRequest_id(),
                RequestStatus.REJECTED
        ));
    }

}
