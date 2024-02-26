package com.virtuocode.publicite.services;

import com.virtuocode.publicite.dto.AnnonceDto;
import com.virtuocode.publicite.entities.Annonce;
import com.virtuocode.publicite.repositories.AnnonceRepository;
import com.virtuocode.publicite.repositories.CibleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final CibleRepository cibleRepository;

    public AnnonceService(AnnonceRepository annonceRepository, CibleRepository cibleRepository) {
        this.annonceRepository = annonceRepository;
        this.cibleRepository = cibleRepository;
    }

    public AnnonceDto getById(Long id) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annonce non trouvée avec l'ID: " + id));

        return new AnnonceDto(annonce);
    }

    public List<AnnonceDto> getAll() {
        List<Annonce> annonces = annonceRepository.findAll();
        return annonces.stream()
                .map(AnnonceDto::new)
                .collect(Collectors.toList());
    }

    public AnnonceDto save(AnnonceDto annonceDto) {
        Annonce newAnnonce = new Annonce(annonceDto);

        Annonce savedAnnonce = annonceRepository.save(newAnnonce);
        return new AnnonceDto(savedAnnonce);

//        cibleRepository.findById(cibleId).map(cible -> {
//            Annonce annonce = new Annonce(annonceDto);
//            annonce.getAnnoncesCiblee().add(cible);
//            log.info("annonce:  "+annonce);
//            return  new AnnonceDto(annonceRepository.save(annonce));
//        });
//        return null;

    }

    // Autres méthodes de service pour la gestion des annonces
}
