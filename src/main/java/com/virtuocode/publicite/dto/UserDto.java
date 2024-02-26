package com.virtuocode.publicite.dto;

import com.virtuocode.publicite.entities.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;
    private List<CampagneDto> campagnes;

    public UserDto(User user) {
        this.id = user.getId();
        this.nom = user.getNom();
        this.email = user.getEmail();
        this.motDePasse = user.getMotDePasse();
//        this.campagnes = user.getCampagnes().stream()
//                .map(CampagneDto::new) // Convertir chaque Campagne en CampagneDto
//                .collect(Collectors.toList()); ;

    }

}
