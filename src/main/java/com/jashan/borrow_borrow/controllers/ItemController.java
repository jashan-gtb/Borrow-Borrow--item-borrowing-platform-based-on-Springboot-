package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository repository;

    @GetMapping("/view")
    public List<Items> findAll(){
        return repository.findAll();
    }

    @GetMapping("/view/{id}")
    public Items findById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/post")
    public Items postUser(@RequestBody Items items){
        return repository.save(items);
    }
    @PutMapping("/update/{id}")
    public Items updateUser(@PathVariable Long id, @RequestBody Items items){
        if (!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return repository.save(items);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
