package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.dto.BorrowRequestSendDto;
import com.jashan.borrow_borrow.dto.RequestStatusDto;
import com.jashan.borrow_borrow.dto.RequestStatusResponseDto;
import com.jashan.borrow_borrow.enums.RequestStatus;
import com.jashan.borrow_borrow.models.BorrowRequest;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.BorrowRequestRepository;
import com.jashan.borrow_borrow.repository.UserRepository;
import com.jashan.borrow_borrow.service.BorrowRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/borrow-requests")
@RequiredArgsConstructor
public class BorrowRequestController {

    private final BorrowRequestService service;
    private final UserRepository userRepository;
    private final BorrowRequestRepository borrowRequestRepository;

    @PostMapping("/send")
    @PreAuthorize("hasRole('ADMIN') or @borrowSecurity.isSenderOfRequest(authentication, #dto)")
    public BorrowRequestSendDto sendRequest(@RequestBody BorrowRequestSendDto dto ){

        return service.sendRequest(dto);
    }
    @PostMapping("/accept")
    @PreAuthorize("hasRole('ADMIN') or @borrowSecurity.isReceiverOfRequest(authentication, #dto)")
    public ResponseEntity<RequestStatusResponseDto> acceptRequest(@RequestBody RequestStatusDto dto){
        Users user= userRepository.findById(dto.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BorrowRequest request= user.getReceiveRequests().stream()
                .filter(borrowRequest -> borrowRequest.getRequestId().equals(dto.requestId()))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.acceptRequest(request);
        if (!request.isActive()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new RequestStatusResponseDto(
                request.getRequestId(),
                RequestStatus.APPROVED
        ));
    }

    @PostMapping("/reject")
    @PreAuthorize("hasRole('ADMIN') or @borrowSecurity.isReceiverOfRequest(authentication, #dto)")
    public ResponseEntity<RequestStatusResponseDto> rejectRequest(@RequestBody RequestStatusDto dto){

        Users user=userRepository.findById(dto.userId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        BorrowRequest request=user.getReceiveRequests().stream()
                .filter(borrowRequest-> borrowRequest.getRequestId().equals(dto.requestId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.rejectRequest(request);
        if (!request.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new RequestStatusResponseDto(
                request.getRequestId(),
                RequestStatus.REJECTED
        ));
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or @borrowSecurity.canDelete(authentication, #id)")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id){
        BorrowRequest request= borrowRequestRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        service.deleteRequest(request);
        return ResponseEntity.ok("Request Deleted");
    }

}
