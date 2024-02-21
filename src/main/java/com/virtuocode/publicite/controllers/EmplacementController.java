package com.virtuocode.publicite.controllers;

import com.virtuocode.publicite.services.EmplacementService;
import com.virtuocode.publicite.dto.EmplacementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emplacements")
public class EmplacementController {

    @Autowired
    EmplacementService emplacementService;

    @GetMapping
    public ResponseEntity<List<EmplacementDto>> getAll() {
        List<EmplacementDto> emplacements = emplacementService.getAll();
        return new ResponseEntity<>(emplacements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmplacementDto> getById(@PathVariable Long id) {
        EmplacementDto emplacementDto = emplacementService.getById(id);
        return new ResponseEntity<>(emplacementDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmplacementDto> add(@RequestBody EmplacementDto emplacementDto) {
        EmplacementDto savedEmplacement = emplacementService.save(emplacementDto);
        return new ResponseEntity<>(savedEmplacement, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmplacementDto> updateEmplacement(@PathVariable Long id, @RequestBody EmplacementDto emplacementDto) {
        EmplacementDto updatedEmplacementDto = emplacementService.updateEmplacement(id, emplacementDto);
        return new ResponseEntity<>(updatedEmplacementDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmplacement(@PathVariable Long id) {
        emplacementService.deleteEmplacement(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}

