package com.jashan.borrow_borrow.service;

import com.jashan.borrow_borrow.dto.BorrowRequestSendDto;
import com.jashan.borrow_borrow.enums.RequestStatus;
import com.jashan.borrow_borrow.models.BorrowRequest;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.BorrowRequestRepository;
import com.jashan.borrow_borrow.repository.ItemRepository;
import com.jashan.borrow_borrow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BorrowRequestService {
    private final BorrowRequestRepository borrowRequestRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Transactional
    public BorrowRequestSendDto sendRequest(BorrowRequestSendDto dto){

        BorrowRequest borrowRequest=new BorrowRequest();
        borrowRequest.setRequestTime(dto.request_time());
        borrowRequest.setRequestStatus(RequestStatus.PENDING);

        Users sender= userRepository.findById(dto.sender_id()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Items items=itemRepository.findById(dto.item_id()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        Users receiver= items.getUser();

        borrowRequest.setItems(items);
        borrowRequestRepository.save(borrowRequest);

        receiver.addReceiveRequest(borrowRequest);
        userRepository.save(receiver);
        sender.addSendRequest(borrowRequest);
        userRepository.save(sender);

        return dto;
    }

    public void acceptRequest(BorrowRequest request){
        if(request.isActive()) {
            request.setRequestStatus(RequestStatus.APPROVED);
            borrowRequestRepository.save(request);
        }
    }
    public void rejectRequest(BorrowRequest request){
        if(request.isActive()) {
            request.setRequestStatus(RequestStatus.REJECTED);
            borrowRequestRepository.save(request);
        }
    }
    public void deleteRequest(BorrowRequest request){
        request.setActive(false);
        borrowRequestRepository.save(request);
    }

}
