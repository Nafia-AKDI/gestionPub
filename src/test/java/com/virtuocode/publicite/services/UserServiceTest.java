package com.virtuocode.publicite.services;

import com.virtuocode.publicite.Exeptions.user.EnregistrementUserException;
import com.virtuocode.publicite.Exeptions.user.UserNonTrouveeException;
import com.virtuocode.publicite.dto.UserDto;
import com.virtuocode.publicite.entities.User;
import com.virtuocode.publicite.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById() {
        // Créez un objet User fictif pour simuler les données de la base de données
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setNom("John Doe");

        // Définissez le comportement du mock UserRepository lorsque la méthode findById est appelée
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Appelez la méthode à tester du service UserService
        UserDto userDto = userService.getById(1L);

        // Vérifiez si le résultat renvoyé correspond à ce à quoi vous vous attendez
        assertEquals(1L, userDto.getId());
        assertEquals("John Doe", userDto.getNom());
    }

    @Test
    public void testGetUserById_WhenUserNotExists() {
        // Définir le comportement du mock UserRepository lorsque la méthode findById est appelée avec un utilisateur non existant
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Appeler la méthode à tester du service UserService et vérifier si elle lance l'exception appropriée
        assertThrows(UserNonTrouveeException.class, () -> userService.getById(1L));
    }

    @Test
    public void testSave_success() {
        // UserDto mock
        UserDto userDto = new UserDto();
        userDto.setNom("JohnDoe");
        userDto.setMotDePasse("password");

        // Saved User mock
        User savedUser = new User(userDto);
        savedUser.setId(1L); // Set an ID for the saved user

        // Mock behavior of userRepository.save()
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Call the method
        UserDto savedUserDto = userService.save(userDto);

        // Assertions
        assertNotNull(savedUserDto);
        assertEquals(userDto.getNom(), savedUserDto.getNom());
        assertEquals(savedUserDto.getId(), savedUser.getId()); // Verify ID is mapped correctly
    }

    @Test
    public void testSaveUser_Failure() {
        // Création d'un objet UserDto fictif pour simuler les données saisies par l'utilisateur
        UserDto userDto = new UserDto();
        userDto.setNom("JohnDoe");
        userDto.setMotDePasse("password");

        // Définir le comportement du mock UserRepository pour lancer une exception lors de l'enregistrement de l'utilisateur
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        // Appeler la méthode à tester du service UserService et vérifier si elle lance l'exception appropriée
        assertThrows(EnregistrementUserException.class, () -> userService.save(userDto));
    }

    @Test
    public void testGetAllUsers() {
        // Création d'une liste d'utilisateurs fictifs pour simuler les données de la base de données

        List<User> userList = Arrays.asList(
                User.builder().id(1L).nom("nom1").motDePasse("password1").build(),
                User.builder().id(2L).nom("nom2").motDePasse("password2").build()
        );

        // Définir le comportement du mock UserRepository lorsque la méthode findAll est appelée
        when(userRepository.findAll()).thenReturn(userList);

        // Appel de la méthode à tester du service UserService
        List<UserDto> userDtoList = userService.getAll();

        // Vérifier si la liste renvoyée correspond à ce à quoi vous vous attendez
        assertEquals(2, userDtoList.size());

        // Vérifier les détails de chaque utilisateur dans la liste
        assertEquals(1L, userDtoList.get(0).getId());
        assertEquals("nom1", userDtoList.get(0).getNom());
        assertEquals(2L, userDtoList.get(1).getId());
        assertEquals("nom2", userDtoList.get(1).getNom());
    }

    @Test
    public void testUpdateUser() {
        // Création d'un utilisateur fictif pour simuler les données de la base de données
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setNom("JohnDoe");
        existingUser.setEmail("john@example.com");
        existingUser.setMotDePasse("oldPassword");

        User updatedUser= new User();
        updatedUser.setNom("JohnUpdated");
        updatedUser.setEmail("john.updated@example.com");
        updatedUser.setMotDePasse("newPassword");

        // Création d'un objet UserDto avec de nouvelles données
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setNom("JohnUpdated");
        updatedUserDto.setEmail("john.updated@example.com");
        updatedUserDto.setMotDePasse("newPassword");

        // Définir le comportement du mock UserRepository lorsque la méthode findById est appelée
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        // Appel de la méthode à tester du service UserService
        UserDto returnedUserDto = userService.updateUser(1L, updatedUserDto);

        // Vérifier si la méthode findById a été appelée avec le bon ID
        verify(userRepository, times(1)).findById(1L);

        // Vérifier si la méthode save a été appelée avec l'user mis à jour
        verify(userRepository, times(1)).save(existingUser);

        // Vérifier si les détails de l'user mis à jour correspondent aux nouvelles données
        assertEquals(updatedUserDto.getNom(), existingUser.getNom());
        assertEquals(updatedUserDto.getEmail(), existingUser.getEmail());
        assertEquals(updatedUserDto.getMotDePasse(), existingUser.getMotDePasse());

        // Vérifier si l'objet UserDto renvoyé correspond aux détails de l'user mis à jour
        assertEquals(updatedUserDto.getNom(), returnedUserDto.getNom());
        assertEquals(updatedUserDto.getEmail(), returnedUserDto.getEmail());
        assertEquals(updatedUserDto.getMotDePasse(), returnedUserDto.getMotDePasse());
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        // Définir le comportement du mock UserRepository lorsque la méthode findById est appelée avec un ID inexistant
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Appeler la méthode à tester du service UserService et vérifier si elle lance l'exception appropriée
        assertThrows(UserNonTrouveeException.class, () -> userService.updateUser(1L, new UserDto()));
    }

    @Test
    public void testDeleteUser_UserFound() {
        // Créer un utilisateur fictif pour simuler l'utilisateur récupéré par findById
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setNom("John Doe");

        // Définir le comportement du mock UserRepository lorsque la méthode findById est appelée avec un ID valide
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // mocking the delete() repo
        doNothing().when(userRepository).delete(mockUser);

        // Test
        userService.deleteUser(1L);

        // Vérifier si la méthode delete a été appelée avec l'utilisateur récupéré
        verify(userRepository, times(1)).delete(mockUser);
    }

    @Test
    public void testDeleteUser_UserNotFound() {
        // Définir le comportement du mock UserRepository lorsque la méthode findById est appelée avec un ID non valide
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        // Test

        doThrow(RuntimeException.class).when(userRepository).delete(new User());

        assertThrows(RuntimeException.class, () -> userService.deleteUser(2L));

        // then

        // Vérifier que la méthode delete n'est jamais appelée
        verify(userRepository, never()).delete(any());

    }

}
