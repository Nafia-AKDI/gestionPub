package com.virtuocode.publicite.dto;

import com.virtuocode.publicite.entities.Campagne;
import com.virtuocode.publicite.entities.Emplacement;
import com.virtuocode.publicite.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampagneDto {

    private Long id;

    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Column(precision = 10, scale = 2)
    private BigDecimal budget;    private String objectif;
    private UserDto user; // Utiliser un objet UserDto au lieu d'un champ userId
    private EmplacementDto emplacement; // Utiliser un objet EmplacementDto au lieu d'un champ emplacementId

    public CampagneDto(Campagne campagne) {
        this.id = campagne.getId();
        this.nom=campagne.getNom();
        this.dateDebut = campagne.getDateDebut();
        this.dateFin = campagne.getDateFin();
        this.budget = campagne.getBudget();
        this.objectif = campagne.getObjectif();
        this.user = new UserDto(campagne.getUser()); // Convertir l'entité User en DTO
        this.emplacement = new EmplacementDto(campagne.getEmplacement()); // Convertir l'entité Emplacement en DTO
    }

}
