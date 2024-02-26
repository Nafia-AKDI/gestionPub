package com.virtuocode.publicite.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.virtuocode.publicite.dto.CibleDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "cibles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String age;

    private String sexe;

    private String localisation;

    @JsonIgnore
    @ManyToMany(mappedBy = "annoncesCiblee")
    private Set<Annonce> annonces = new HashSet<>();


    @Override
    public String toString() {
        return "Cible{" +
                "id=" + id +
                ", age='" + age + '\'' +
                ", sexe='" + sexe + '\'' +
                ", localisation='" + localisation + '\'' +
                ", annonces=" + annonces +
                '}';
    }

    public Cible(CibleDto cibleDto) {
        this.id = cibleDto.getId();
        this.age = cibleDto.getAge();
        this.sexe = cibleDto.getSexe();
        this.localisation = cibleDto.getLocalisation();
    }

}
