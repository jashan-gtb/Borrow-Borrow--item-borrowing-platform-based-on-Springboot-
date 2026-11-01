package com.jashan.borrow_borrow.mapper;

import com.jashan.borrow_borrow.dto.BorrowRequestSummaryDTO;
import com.jashan.borrow_borrow.dto.ItemDto;
import com.jashan.borrow_borrow.dto.UserResponseDto;
import com.jashan.borrow_borrow.models.BorrowRequest;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponseDto touserResponseDto(Users user){
        if (user==null){
            return null;
        }
        List<ItemDto> itemDtos= safeStream(user.getItems()).map(UserMapper:: toitemDto)
                .collect(Collectors.toList());
        List<BorrowRequestSummaryDTO> sendDtos= safeStream(user.getSendRequests())
                .map(UserMapper::toBorrowRequests)
                .collect(Collectors.toList());
        List<BorrowRequestSummaryDTO> receivedDTOs = safeStream(user.getReceiveRequests())
                .map(UserMapper::toBorrowRequests)
                .collect(Collectors.toList());

        return new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                itemDtos,
                sendDtos,
                receivedDTOs
        );
    }
    public static ItemDto toitemDto(Items item){
        if (item==null){
            return null;
        }
        if (!item.isActive()){
            return null;
        }
        return new ItemDto(
                item.getItemId(),
                item.getItemName(),
                item.getItemDescription(),
                item.getItemImage()
                );
    }
    public static BorrowRequestSummaryDTO toBorrowRequests(BorrowRequest request){
        if (request==null){
            return null;
        }
        if (!request.isActive()){
            return null;
        }
        ItemDto itemDto=UserMapper.toitemDto(request.getItems());
        if(itemDto==null){
            return null;
        }
        List<ItemDto> requestItems= itemDto!=null ?List.of(itemDto):List.of();


        return new BorrowRequestSummaryDTO(
                request.getRequestId(),
                request.getRequestTime(),
                request.getRequestStatus(),
                request.getSender() != null ? request.getSender().getUserId() : null,
                request.getSender() != null ? request.getSender().getUsername() : null,
                request.getReceiver() != null ? request.getReceiver().getUserId() : null,
                requestItems
        );
    }

    private static <T> java.util.stream.Stream<T> safeStream(List<T> list) {
        return list== null ? java.util.stream.Stream.empty() : list.stream();
    }
}
