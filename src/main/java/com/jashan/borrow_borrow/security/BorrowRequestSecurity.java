package com.jashan.borrow_borrow.security;

import com.jashan.borrow_borrow.dto.BorrowRequestSendDto;
import com.jashan.borrow_borrow.dto.RequestStatusDto;
import com.jashan.borrow_borrow.models.BorrowRequest;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.BorrowRequestRepository;
import com.jashan.borrow_borrow.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("borrowSecurity")
@RequiredArgsConstructor
public class BorrowRequestSecurity {
    private final BorrowRequestRepository requestRepository;
    private final ItemRepository itemRepository;

    public boolean isSenderOfRequest(Authentication authentication, BorrowRequestSendDto dto){

        Users principle=(Users) authentication.getPrincipal();

        if (principle==null)return false;
        return dto.sender_id().equals(principle.getUserId());

    }

    public boolean isReceiverOfRequest(Authentication authentication, RequestStatusDto dto){
        Users principle=(Users) authentication.getPrincipal();

        if (principle==null)return false;

        return requestRepository.findById(dto.requestId())
                .map(request -> request.getReceiver().getUserId().equals(dto.userId()))
                .orElse(false);
    }

    public boolean canDelete(Authentication authentication, Long id){
        Users principle= (Users) authentication.getPrincipal();
        if (principle==null) {return false;}
        return  requestRepository.findById(id)
                .map(request -> request.getSender().getUserId().equals(principle.getUserId()))
                .orElse(false);
    }

    public boolean isOwnerOfItem(Authentication authentication, Long id){
        Users principle= (Users) authentication.getPrincipal();
        if (principle==null) {return false;}

        return itemRepository.findById(id)
                .map(items -> items.getUser().getUserId().equals(principle.getUserId()))
                .orElse(false);
    }

}
