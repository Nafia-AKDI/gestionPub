package com.virtuocode.publicite.repositories;

import com.virtuocode.publicite.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnonceRepository extends JpaRepository<Annonce,Long> {

}