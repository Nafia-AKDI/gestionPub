package com.virtuocode.publicite.controllers;

import com.virtuocode.publicite.dto.CampagneDto;
import com.virtuocode.publicite.services.CampagneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campagnes")
@Slf4j
public class CampagneController {

    private final CampagneService campagneService;

    public CampagneController(CampagneService campagneService) {
        this.campagneService = campagneService;
    }

    @GetMapping
    public ResponseEntity<List<CampagneDto>> getAll() {
        List<CampagneDto> campagnes = campagneService.getAll();
        return new ResponseEntity<>(campagnes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampagneDto> getById(@PathVariable Long id) {
        CampagneDto campagneDto = campagneService.getById(id);
        return new ResponseEntity<>(campagneDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CampagneDto> add(@RequestBody CampagneDto campagneDto) {
        CampagneDto savedCampagne = campagneService.save(campagneDto);
        return new ResponseEntity<>(savedCampagne, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampagneDto> updateCampagne(@PathVariable Long id, @RequestBody CampagneDto campagneDto) {
        CampagneDto updatedCampagneDto = campagneService.updateCampagne(id, campagneDto);
        return new ResponseEntity<>(updatedCampagneDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampagne(@PathVariable Long id) {
        campagneService.deleteCampagne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

