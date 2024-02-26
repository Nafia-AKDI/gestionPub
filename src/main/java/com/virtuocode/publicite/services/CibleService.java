package com.virtuocode.publicite.services;

import com.virtuocode.publicite.dto.CibleDto;
import com.virtuocode.publicite.entities.Cible;
import com.virtuocode.publicite.repositories.CibleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CibleService {

    private final CibleRepository cibleRepository;

    public CibleService(CibleRepository cibleRepository) {
        this.cibleRepository = cibleRepository;
    }

    public CibleDto getById(Long id) {
        Cible cible = cibleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cible non trouvée avec l'ID: " + id));
        return new CibleDto(cible);
    }

    public List<CibleDto> getAll() {
        List<Cible> cibles = cibleRepository.findAll();
        return cibles.stream()
                .map(CibleDto::new)
                .collect(Collectors.toList());
    }

    public CibleDto save(CibleDto cibleDto) {
        Cible newCible = new Cible(cibleDto);
        Cible savedCible = cibleRepository.save(newCible);
        return new CibleDto(savedCible);
    }

    // Autres méthodes de service pour la gestion des cibles
}
