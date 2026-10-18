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

    @PostMapping("/send/{idU}/{idI}")
    public BorrowRequestSendDto sendRequest(@RequestBody BorrowRequestSendDto dto ,
                                            @PathVariable Long idU,
                                            @PathVariable Long idI){
        Users user= userRepository.findById(idU).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Items item= itemRepository.findById(idI).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return service.sendRequest(dto,user,item);
    }

}
