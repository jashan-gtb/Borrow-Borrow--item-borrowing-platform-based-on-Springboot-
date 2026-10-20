package com.jashan.borrow_borrow.controllers;

import com.jashan.borrow_borrow.dto.ItemDto;
import com.jashan.borrow_borrow.dto.UserResponseDto;
import com.jashan.borrow_borrow.mapper.UserMapper;
import com.jashan.borrow_borrow.models.Items;
import com.jashan.borrow_borrow.models.Users;
import com.jashan.borrow_borrow.repository.UserRepository;
import com.jashan.borrow_borrow.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final ItemService itemService;

    @GetMapping("/view")
    public ResponseEntity<List<UserResponseDto>> findAllUser(){

        List<UserResponseDto> responseDtos= repository.findAll().stream()//takes all users to a conveyor belt one by one
                .map(UserMapper::touserResponseDto)// converts each user into ResponseDto by passing them in touserResponseDto
                .collect(Collectors.toList());// collects responseDtos and returns list

        return ResponseEntity.ok(responseDtos);
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id){
        Users user =repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        UserResponseDto response= UserMapper.touserResponseDto(user);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/post")
    public Users postUser(@RequestBody Users users){
        return repository.save(users);
    }
    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users users){
        if (!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return repository.save(users);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PostMapping("/post-item/{id}")
    public ResponseEntity<ItemDto> postItems(@RequestBody ItemDto dto, @PathVariable Long id){
        Users user= repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return itemService.postItems(dto,user);
    }



}
