package com.jashan.borrow_borrow.service;

import com.jashan.borrow_borrow.dto.ItemDto;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ResponseEntity<ItemDto> postItems(ItemDto dto, Users user){

        Items item= new Items();

        item.setItemName(dto.item_name());
        item.setItemDescription(dto.item_description());
        item.setItemImage(dto.item_image());
        item.setUser(user);
        Items savedItem =itemRepository.save(item);

        ItemDto itemDto= new ItemDto(
                savedItem.getItemId(),
                savedItem.getItemName(),
                savedItem.getItemDescription(),
                savedItem.getItemImage()
        );

        return ResponseEntity.ok(itemDto);

    }
    public void  deleteItem(Long id){
        Items item=itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        item.setActive(false);
        itemRepository.save(item);
    }


}
