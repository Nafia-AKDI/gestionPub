package com.virtuocode.publicite.entities;
import com.virtuocode.publicite.dto.EmplacementDto;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "emplacements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Emplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;
    private String format;
    @Column(precision = 10, scale = 2)
    private BigDecimal prix;

    @OneToMany(mappedBy = "emplacement", cascade = CascadeType.ALL) // Utilisez le nom de la propriété dans Campagne
    private List<Campagne> campagnes;




    public EmplacementDto toDto(){
        return new EmplacementDto(this);
    }

    public Emplacement(EmplacementDto emplacementDto) {
        this.id = emplacementDto.getId();
        this.nom = emplacementDto.getNom();
        this.type = emplacementDto.getType();
        this.format = emplacementDto.getFormat();
        this.prix = emplacementDto.getPrix();
    }

}