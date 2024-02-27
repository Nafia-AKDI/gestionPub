package com.virtuocode.publicite.controller;

import com.virtuocode.publicite.controllers.UserController;
import com.virtuocode.publicite.dto.UserDto;
import com.virtuocode.publicite.entities.User;
import com.virtuocode.publicite.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        List<UserDto> users = Arrays.asList(
                UserDto.builder().id(1L).nom("nom1").motDePasse("password1").build(),
                UserDto.builder().id(2L).nom("nom2").motDePasse("password2").build()
        );

        when(userService.getAll()).thenReturn(users);

        ResponseEntity<List<UserDto>> responseEntity = userController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    public void testGetById() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setNom("John Doe");
        when(userService.getById(1L)).thenReturn(userDto);

        ResponseEntity<UserDto> responseEntity = userController.getById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    public void testAdd() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setNom("John Doe");
        when(userService.save(userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> responseEntity = userController.add(userDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setNom("John Doe");
        when(userService.updateUser(1L, userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> responseEntity = userController.updateUser(1L, userDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        // Mock UserService behavior
        doNothing().when(userService).deleteUser(userId);

        // Call the method
        ResponseEntity<Void> responseEntity = userController.deleteUser(userId);

        // Assertions
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}
