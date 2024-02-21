package com.virtuocode.publicite.repositories;

import com.virtuocode.publicite.entities.Campagne;
import com.virtuocode.publicite.entities.Emplacement;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CampagneRepository extends JpaRepository<Campagne,Long> {

}
