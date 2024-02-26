package com.virtuocode.publicite.controllers;

import com.virtuocode.publicite.dto.AnnonceDto;
import com.virtuocode.publicite.services.AnnonceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    private final AnnonceService annonceService;


    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceDto> getById(@PathVariable Long id) {
        AnnonceDto annonceDto = annonceService.getById(id);
        return new ResponseEntity<>(annonceDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<AnnonceDto> add(@RequestBody AnnonceDto annonceDto) {

        AnnonceDto createdAnnonce = annonceService.save(annonceDto);
        return new ResponseEntity<>(createdAnnonce, HttpStatus.CREATED);
    }

    // Ajoutez d'autres méthodes de contrôleur selon vos besoins
}
