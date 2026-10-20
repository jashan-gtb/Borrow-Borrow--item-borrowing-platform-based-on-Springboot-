package com.jashan.borrow_borrow.mapper;

import com.jashan.borrow_borrow.dto.BorrowRequestSummaryDTO;
import com.jashan.borrow_borrow.dto.ItemDto;
import com.jashan.borrow_borrow.dto.UserResponseDto;
import com.jashan.borrow_borrow.models.BorrowRequest;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import org.apache.catalina.User;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.ServiceLoaderUtil.safeStream;

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
                user.getUser_id(),
                user.getUser_name(),
                user.getSendRequestStatus(),
                user.getReceiveRequestStatus(),
                itemDtos,
                sendDtos,
                receivedDTOs
        );
    }
    public static ItemDto toitemDto(Items item){
        if (item==null){
            return null;
        }
        return new ItemDto(
                item.getItem_id(),
                item.getItem_name(),
                item.getItem_description(),
                item.getItem_image()
                );
    }
    public static BorrowRequestSummaryDTO toBorrowRequests(BorrowRequest request){
        if (request==null){
            return null;
        }
        ItemDto itemDto=UserMapper.toitemDto(request.getItems());
        List<ItemDto> requestItems= itemDto!=null ?List.of(itemDto):List.of();


        return new BorrowRequestSummaryDTO(
                request.getRequest_id(),
                request.getRequest_time(),
                request.getRequestStatus(),
                request.getSender() != null ? request.getSender().getUser_id() : null,
                request.getSender() != null ? request.getSender().getUser_name() : null,
                request.getReceiver() != null ? request.getReceiver().getUser_id() : null,
                requestItems
        );
    }

    private static <T> java.util.stream.Stream<T> safeStream(List<T> list) {
        return list== null ? java.util.stream.Stream.empty() : list.stream();
    }
}
