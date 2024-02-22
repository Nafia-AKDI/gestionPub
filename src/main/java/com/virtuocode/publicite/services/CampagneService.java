package com.virtuocode.publicite.services;

import com.virtuocode.publicite.Exeptions.campagne.CampagneNonTrouveeException;
import com.virtuocode.publicite.Exeptions.campagne.EnregistrementCampagneException;
import com.virtuocode.publicite.dto.CampagneDto;
import com.virtuocode.publicite.entities.Campagne;
import com.virtuocode.publicite.entities.User;
import com.virtuocode.publicite.repositories.CampagneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CampagneService {


    private final CampagneRepository campagneRepository;

    public CampagneService(CampagneRepository campagneRepository) {
        this.campagneRepository = campagneRepository;
    }

    public CampagneDto getById(Long id) {
        Optional<Campagne> optionalCampagne = campagneRepository.findById(id);
        log.info("camgne found");
        if (optionalCampagne.isPresent()) {
            Campagne campagne = optionalCampagne.get();
            return new CampagneDto(campagne);
        } else {
            throw new CampagneNonTrouveeException(id);
        }
    }

    public CampagneDto save(CampagneDto campagneDto) {
        Campagne newCampagne = new Campagne(campagneDto);

        try {
            Campagne savedCampagne = campagneRepository.save(newCampagne);
            return new CampagneDto(savedCampagne);
        } catch (Exception e) {
            throw new EnregistrementCampagneException();
        }
    }

    public List<CampagneDto> getAll() {
        List<Campagne> campagnes =  campagneRepository.findAll();
        return campagnes.stream()
                .map(CampagneDto::new) // Convertir chaque Campagne en CampagneDto
                .collect(Collectors.toList()); // Collecter les CampagneDto dans une liste
    }

    public CampagneDto updateCampagne(Long id, CampagneDto campagneDto) {
        // Vérifier si l'campagne avec l'ID spécifié existe
        Campagne existingCampagne = campagneRepository.findById(id)
                .orElseThrow(() -> new CampagneNonTrouveeException(id));

        // Mettre à jour les champs de l'campagne existant avec les nouvelles données

        existingCampagne.setNom(campagneDto.getNom());
        existingCampagne.setBudget(campagneDto.getBudget());
        existingCampagne.setDateDebut(campagneDto.getDateFin());
        existingCampagne.setDateFin(campagneDto.getDateFin());
        existingCampagne.setObjectif(campagneDto.getObjectif());
        existingCampagne.setUser(User.builder()
                .id(campagneDto.getUser().getId())
                .build());


        // Enregistrer l'campagne mis à jour dans la base de données
        Campagne updatedCampagne = campagneRepository.save(existingCampagne);

        // Retourner l'objet CampagneDto correspondant à l'campagne mis à jour
        return new CampagneDto(updatedCampagne);
    }


    public void deleteCampagne(Long id) {
        Campagne campagne = campagneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campagne non trouvé avec l'ID: " + id));

        // Supprimer l'campagne de la base de données
        campagneRepository.delete(campagne);
    }

}