package com.virtuocode.publicite.dto;

import com.virtuocode.publicite.entities.Cible;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CibleDto {

    private Long id;
    private String age;
    private String sexe;
    private String localisation;


    public CibleDto(Cible cible) {
        this.id = cible.getId();
        this.age = cible.getAge();
        this.sexe = cible.getSexe();
        this.localisation = cible.getLocalisation();
    }

}
