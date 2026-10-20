package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.dto.BorrowRequestSendDto;
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

}
