package com.virtuocode.publicite.controllers;

import com.virtuocode.publicite.dto.CibleDto;
import com.virtuocode.publicite.services.CibleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cibles")
public class CibleController {

    private final CibleService cibleService;

    public CibleController(CibleService cibleService) {
        this.cibleService = cibleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CibleDto> getById(@PathVariable Long id) {
        CibleDto cibleDto = cibleService.getById(id);
        return new ResponseEntity<>(cibleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CibleDto> create(@RequestBody CibleDto cibleDto) {
        CibleDto createdCible = cibleService.save(cibleDto);
        return new ResponseEntity<>(createdCible, HttpStatus.CREATED);
    }


}
