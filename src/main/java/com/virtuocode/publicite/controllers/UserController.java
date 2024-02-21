package com.virtuocode.publicite.controllers;

import com.virtuocode.publicite.services.UserService;
import com.virtuocode.publicite.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        UserDto userDto = userService.getById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> add(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.save(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(id, userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}

