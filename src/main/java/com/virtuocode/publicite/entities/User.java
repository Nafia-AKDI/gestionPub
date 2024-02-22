package com.virtuocode.publicite.entities;

import com.virtuocode.publicite.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Utilisez le nom de la propriété dans Campagne
    private List<Campagne> campagnes;


    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.nom = userDto.getNom();
        this.email = userDto.getEmail();
        this.motDePasse = userDto.getMotDePasse();

    }


}
