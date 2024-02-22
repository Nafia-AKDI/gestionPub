package com.virtuocode.publicite.dto;

import com.virtuocode.publicite.entities.Emplacement;
import com.virtuocode.publicite.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    }
}
