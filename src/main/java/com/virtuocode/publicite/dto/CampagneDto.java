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
@EqualsAndHashCode
public class CampagneDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Column(precision = 10, scale = 2)
    private BigDecimal budget;    private String objectif;
    private Long userId;
    private Long emplacementId;



    public CampagneDto(Campagne canpagne) {
        this.id = canpagne.getId();
        this.dateDebut = canpagne.getDateDebut();
        this.dateFin = canpagne.getDateFin();
        this.budget = canpagne.getBudget();
        this.objectif = canpagne.getObjectif();
        this.userId=canpagne.getUser().getId();
        this.emplacementId =canpagne.getEmplacement().getId();
    }

    public Campagne toEntity(){
        return new Campagne(this);
    }

}
