package com.springdemos.springdemos.controller;

import com.springdemos.springdemos.data.dto.UserCreateDto;
import com.springdemos.springdemos.entity.User;
import com.springdemos.springdemos.service.user.contracts.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok(userService.listUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @ModelAttribute UserCreateDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @Valid @ModelAttribute UserCreateDto requestDto) {
        return ResponseEntity.ok(userService.updateUser(id, requestDto));
    }
}
