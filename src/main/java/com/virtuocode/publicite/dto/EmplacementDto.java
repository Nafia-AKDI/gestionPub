package com.virtuocode.publicite.dto;

import com.virtuocode.publicite.entities.Emplacement;
import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmplacementDto {

    private Long id;

    private String nom;
    private String type;
    private String format;
    @Column(precision = 10, scale = 2)
    private BigDecimal prix;
    private List<CampagneDto> campagnes;

    public EmplacementDto(Emplacement emplacement) {
        this.id = emplacement.getId();
        this.nom = emplacement.getNom();
        this.type = emplacement.getType();
        this.format = emplacement.getFormat();
        this.prix = emplacement.getPrix();
    }

}
