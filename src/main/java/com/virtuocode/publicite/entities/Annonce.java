package com.virtuocode.publicite.entities;

import com.virtuocode.publicite.dto.AnnonceDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "annonces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Annonce {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private LocalDate dateDebut;

    private LocalDate dateFin;


    private String description;

    private String lienImage;


    @ManyToMany
    @JoinTable(name = "annonces_cibles", joinColumns = {@JoinColumn(name = "annonce_id")}, inverseJoinColumns = {
            @JoinColumn(name = "cible_id")})
    private Set<Cible> annoncesCiblee = new HashSet<>();


    @Override
    public String toString() {
        return "Annonce{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", description='" + description + '\'' +
                ", lienImage='" + lienImage + '\'' +
                ", cibles=" + annoncesCiblee +
                '}';
    }

    public Annonce(AnnonceDto annonceDto) {
        this.id = annonceDto.getId();
        this.titre = annonceDto.getTitre();
        this.dateDebut = annonceDto.getDateDebut();
        this.dateFin = annonceDto.getDateFin();
        this.description = annonceDto.getDescription();
        this.lienImage = annonceDto.getLienImage();
        annoncesCiblee = annonceDto.getAnnoncesCiblee();
    }
}
