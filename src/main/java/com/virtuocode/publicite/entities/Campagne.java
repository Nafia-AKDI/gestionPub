package com.virtuocode.publicite.entities;
import com.virtuocode.publicite.dto.CampagneDto;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "campagnes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Campagne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Column(precision = 10, scale = 2)
    private BigDecimal budget;
    private String objectif;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "emplacement_id",nullable = false)
    private Emplacement emplacement;

    public CampagneDto toDto(){
        return new CampagneDto(this);
    }

    public Campagne(CampagneDto campagneDto) {
        this.id = campagneDto.getId();
        this.dateDebut = campagneDto.getDateDebut();
        this.dateFin = campagneDto.getDateFin();
        this.budget = campagneDto.getBudget();
        this.objectif = campagneDto.getObjectif();
        this.user= campagneDto.getUser();
        this.emplacement =campagneDto.getEmplacement();
    }

}
