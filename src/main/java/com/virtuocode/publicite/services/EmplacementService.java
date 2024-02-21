package com.virtuocode.publicite.services;

import com.virtuocode.publicite.dto.EmplacementDto;
import com.virtuocode.publicite.entities.Emplacement;
import com.virtuocode.publicite.repositories.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmplacementService {

    @Autowired
    EmplacementRepository emplacementRepository;

    public EmplacementDto getById(Long id) {
        Optional<Emplacement> optionalEmplacement = emplacementRepository.findById(id);
        if (optionalEmplacement.isPresent()) {
            Emplacement emplacement = optionalEmplacement.get();
            return new EmplacementDto(emplacement);
        } else {
            throw new RuntimeException("Emplacement non trouvé avec l'ID: " + id);
        }
    }

    public EmplacementDto save(EmplacementDto emplacementDto) {
        Emplacement newEmplacement = new Emplacement(emplacementDto);

        try {
            Emplacement savedEmplacement = emplacementRepository.save(newEmplacement);
            return new EmplacementDto(savedEmplacement);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'enregistrement de l'emplacement");
        }
    }

    public List<EmplacementDto> getAll() {
        List<Emplacement> emplacements = emplacementRepository.findAll();
        return emplacements.stream()
                .map(EmplacementDto::new) // Convertir chaque Emplacement en EmplacementDto
                .collect(Collectors.toList()); // Collecter les EmplacementDto dans une liste
    }

    public EmplacementDto updateEmplacement(Long id, EmplacementDto emplacementDto) {
        // Vérifier si l'emplacement avec l'ID spécifié existe
        Emplacement existingEmplacement = emplacementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Emplacement non trouvé avec l'ID: " + id));

        // Mettre à jour les champs de l'emplacement existant avec les nouvelles données
        existingEmplacement.setNom(emplacementDto.getNom());
        existingEmplacement.setType(emplacementDto.getType());
        existingEmplacement.setFormat(emplacementDto.getFormat());
        existingEmplacement.setPrix(emplacementDto.getPrix());

        // Enregistrer l'emplacement mis à jour dans la base de données
        Emplacement updatedEmplacement = emplacementRepository.save(existingEmplacement);

        // Retourner l'objet EmplacementDto correspondant à l'emplacement mis à jour
        return new EmplacementDto(updatedEmplacement);
    }

//    public void delete(Long id) {
//        emplacementRepository.deleteById(id);
//    }
//
//    public Emplacement getEmplacementByWalletAddress(String walletAddress) {
//        return emplacementRepository.findEmplacementByWalletAddress(walletAddress);
//    }
public void deleteEmplacement(Long id) {
    Emplacement emplacement = emplacementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Emplacement non trouvé avec l'ID: " + id));

    // Supprimer l'emplacement de la base de données
    emplacementRepository.delete(emplacement);
}

}