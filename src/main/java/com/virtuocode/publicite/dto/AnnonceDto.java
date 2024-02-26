package com.virtuocode.publicite.dto;

import com.virtuocode.publicite.entities.Annonce;
import com.virtuocode.publicite.entities.Cible;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnonceDto {

    private Long id;

    private String titre;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String description;

    private String lienImage;

    private Set<Cible> annoncesCiblee = new HashSet<>();

    public AnnonceDto(Annonce annonce) {
        this.id = annonce.getId();
        this.titre = annonce.getTitre();
        this.dateDebut = annonce.getDateDebut();
        this.dateFin = annonce.getDateFin();
        this.description = annonce.getDescription();
        this.lienImage = annonce.getLienImage();
        annoncesCiblee = annonce.getAnnoncesCiblee();
    }
}