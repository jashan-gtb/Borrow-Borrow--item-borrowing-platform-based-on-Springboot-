package com.jashan.borrow_borrow.service;

import com.jashan.borrow_borrow.dto.ItemDto;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.ItemRepository;
import com.jashan.borrow_borrow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ResponseEntity<ItemDto> postItems(ItemDto dto, Users user){

        Items item= new Items();

        item.setItem_name(dto.item_name());
        item.setItem_description(dto.item_description());
        item.setItem_image(dto.item_image());
        item.setUser(user);
        Items savedItem =itemRepository.save(item);

        ItemDto itemDto= new ItemDto(
                savedItem.getItem_id(),
                savedItem.getItem_name(),
                savedItem.getItem_description(),
                savedItem.getItem_image()
        );

        return ResponseEntity.ok(itemDto);

    }
}
