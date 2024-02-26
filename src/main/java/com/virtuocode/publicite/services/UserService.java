package com.virtuocode.publicite.services;

import com.virtuocode.publicite.Exeptions.user.EnregistrementUserException;
import com.virtuocode.publicite.Exeptions.user.UserNonTrouveeException;
import com.virtuocode.publicite.dto.UserDto;
import com.virtuocode.publicite.entities.User;
import com.virtuocode.publicite.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDto(user);
        } else {
            throw new UserNonTrouveeException(id);
        }
    }

    public UserDto save(UserDto userDto) {
        User newUser = new User(userDto);

        try {
            User savedUser = userRepository.save(newUser);
            return new UserDto(savedUser);
        } catch (Exception e) {
            throw new EnregistrementUserException();
        }
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::new) // Convertir chaque User en UserDto
                .collect(Collectors.toList()); // Collecter les UserDto dans une liste
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        // Vérifier si l'user avec l'ID spécifié existe
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User non trouvé avec l'ID: " + id));

        // Mettre à jour les champs de l'user existant avec les nouvelles données
        existingUser.setNom(userDto.getNom());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setMotDePasse(userDto.getMotDePasse());

        // Enregistrer l'user mis à jour dans la base de données
        User updatedUser = userRepository.save(existingUser);

        // Retourner l'objet UserDto correspondant à l'user mis à jour
        return new UserDto(updatedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User non trouvé avec l'ID: " + id));

        // Supprimer l'user de la base de données
        userRepository.delete(user);
    }

}