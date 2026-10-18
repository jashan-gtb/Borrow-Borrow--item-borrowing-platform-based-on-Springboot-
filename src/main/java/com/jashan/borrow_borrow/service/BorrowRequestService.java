package com.jashan.borrow_borrow.service;

import com.jashan.borrow_borrow.dto.BorrowRequestSendDto;
import com.jashan.borrow_borrow.enums.RequestStatus;
import com.jashan.borrow_borrow.models.BorrowRequestSend;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.BorrowRequestRepository;
import com.jashan.borrow_borrow.repository.ItemRepository;
import com.jashan.borrow_borrow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowRequestService {
    private final BorrowRequestRepository borrowRequestRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public BorrowRequestSendDto sendRequest(BorrowRequestSendDto dto, Users user, Items item){

        BorrowRequestSend borrowRequest=new BorrowRequestSend();
        borrowRequest.setRequest_time(dto.request_time());
        borrowRequest.setRequestStatus(RequestStatus.PENDING);
        if (userRepository.existsById(dto.user_id())){
            borrowRequest.setUser(user);
        }
        if (itemRepository.existsById(dto.item_id())){
            borrowRequest.setItems(item);
        }


        borrowRequestRepository.save(borrowRequest);
        return dto;
    }
}
