package com.virtuocode.publicite.services;

import com.virtuocode.publicite.dto.CampagneDto;
import com.virtuocode.publicite.entities.Campagne;
import com.virtuocode.publicite.repositories.CampagneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampagneService {

    @Autowired
    CampagneRepository emplacementRepository;

    public CampagneDto getById(Long id) {
        Optional<Campagne> optionalCampagne = emplacementRepository.findById(id);
        if (optionalCampagne.isPresent()) {
            Campagne emplacement = optionalCampagne.get();
            return new CampagneDto(emplacement);
        } else {
            throw new RuntimeException("Campagne non trouvé avec l'ID: " + id);
        }
    }

    public CampagneDto save(CampagneDto emplacementDto) {
        Campagne newCampagne = new Campagne(emplacementDto);

        try {
            Campagne savedCampagne = emplacementRepository.save(newCampagne);
            return new CampagneDto(savedCampagne);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'enregistrement de l'emplacement");
        }
    }

    public List<CampagneDto> getAll() {
        List<Campagne> emplacements = emplacementRepository.findAll();
        return emplacements.stream()
                .map(CampagneDto::new) // Convertir chaque Campagne en CampagneDto
                .collect(Collectors.toList()); // Collecter les CampagneDto dans une liste
    }

    public CampagneDto updateCampagne(Long id, CampagneDto emplacementDto) {
        // Vérifier si l'emplacement avec l'ID spécifié existe
        Campagne existingCampagne = emplacementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvé avec l'ID: " + id));

        // Mettre à jour les champs de l'emplacement existant avec les nouvelles données
        existingCampagne.setNom(emplacementDto.getNom());
        existingCampagne.setBudget(emplacementDto.getBudget());
        existingCampagne.setDateDebut(emplacementDto.getDateFin());
        existingCampagne.setDateFin(emplacementDto.getDateFin());
        existingCampagne.setObjectif(emplacementDto.getObjectif());
        existingCampagne.setUser(emplacementDto.getUser());

        // Enregistrer l'emplacement mis à jour dans la base de données
        Campagne updatedCampagne = emplacementRepository.save(existingCampagne);

        // Retourner l'objet CampagneDto correspondant à l'emplacement mis à jour
        return new CampagneDto(updatedCampagne);
    }


    public void deleteCampagne(Long id) {
        Campagne emplacement = emplacementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvé avec l'ID: " + id));

        // Supprimer l'emplacement de la base de données
        emplacementRepository.delete(emplacement);
    }

}