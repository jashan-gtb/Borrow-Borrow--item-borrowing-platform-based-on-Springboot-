package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.ItemRepository;
import com.jashan.borrow_borrow.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or @borrowSecurity.isOwnerOfItem(authentication, #id)")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteItem(id);
        return ResponseEntity.ok("Item Deleted");
    }
}
