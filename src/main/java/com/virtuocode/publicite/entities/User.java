package com.virtuocode.publicite.entities;
import com.virtuocode.publicite.dto.CampagneDto;
import com.virtuocode.publicite.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;


    public UserDto toDto(){
        return new UserDto(this);
    }

    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.nom = userDto.getNom();
        this.email = userDto.getEmail();
        this.motDePasse = userDto.getMotDePasse();
    }

}
