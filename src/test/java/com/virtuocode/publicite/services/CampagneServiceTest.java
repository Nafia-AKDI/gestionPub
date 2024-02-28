package com.virtuocode.publicite.services;

import com.virtuocode.publicite.Exeptions.campagne.CampagneNonTrouveeException;
import com.virtuocode.publicite.Exeptions.campagne.EnregistrementCampagneException;
import com.virtuocode.publicite.dto.CampagneDto;
import com.virtuocode.publicite.dto.EmplacementDto;
import com.virtuocode.publicite.dto.UserDto;
import com.virtuocode.publicite.entities.Campagne;
import com.virtuocode.publicite.entities.Emplacement;
import com.virtuocode.publicite.entities.User;
import com.virtuocode.publicite.repositories.CampagneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CampagneServiceTest {
    @Mock
    private CampagneRepository campagneRepository;

    @InjectMocks
    private CampagneService campagneService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCampagneById() {
        // Création d'une campagne fictive pour simuler les données de la base de données
        Campagne mockCampagne = new Campagne();
        mockCampagne.setId(1L);
        mockCampagne.setNom("Campagne 1");
        mockCampagne.setUser(new User());
        mockCampagne.setEmplacement(new Emplacement());

        // Définir le comportement du mock CampagneRepository lorsque la méthode findById est appelée
        when(campagneRepository.findById(1L)).thenReturn(Optional.of(mockCampagne));

        // Appel de la méthode à tester du service CampagneService
        CampagneDto campagneDto = campagneService.getById(1L);

        // Vérification si le résultat renvoyé correspond à ce à quoi vous vous attendez
        assertEquals(1L, campagneDto.getId());
        assertEquals("Campagne 1", campagneDto.getNom());
    }

    @Test
    public void testGetCampagneById_WhenCampagneNotExists() {
        // Définir le comportement du mock CampagneRepository lorsque la méthode findById est appelée avec une campagne non existante
        when(campagneRepository.findById(1L)).thenReturn(Optional.empty());

        // Appel de la méthode à tester du service CampagneService et vérification si elle lance l'exception appropriée
        assertThrows(CampagneNonTrouveeException.class, () -> campagneService.getById(1L));
    }

    @Test
    public void testSaveCampagne_Success() {
        // CampagneDto fictive
        CampagneDto campagneDto = new CampagneDto();
        campagneDto.setNom("Campagne 1");
        campagneDto.setUser(new UserDto());
        campagneDto.setEmplacement(new EmplacementDto());

        // Campagne enregistrée fictive
        Campagne savedCampagne = new Campagne(campagneDto);
        savedCampagne.setId(1L); // Définir un ID pour la campagne enregistrée

        // Définir le comportement du mock CampagneRepository lorsque la méthode save est appelée
        when(campagneRepository.save(any(Campagne.class))).thenReturn(savedCampagne);

        // Appel de la méthode à tester du service CampagneService
        CampagneDto savedCampagneDto = campagneService.save(campagneDto);

        // Assertions
        assertNotNull(savedCampagneDto);
        assertEquals(campagneDto.getNom(), savedCampagneDto.getNom());
        assertEquals(savedCampagneDto.getId(), savedCampagne.getId()); // Vérifier que l'ID est correctement mappé
    }

    @Test
    public void testSaveCampagne_Failure() {
        // CampagneDto fictive
        CampagneDto campagneDto = new CampagneDto();
        campagneDto.setNom("Campagne 1");
        campagneDto.setUser(new UserDto());
        campagneDto.setEmplacement(new EmplacementDto());

        // Définir le comportement du mock CampagneRepository pour lancer une exception lors de l'enregistrement de la campagne
        when(campagneRepository.save(any(Campagne.class))).thenThrow(new RuntimeException());

        // Appel de la méthode à tester du service CampagneService et vérification si elle lance l'exception appropriée
        assertThrows(EnregistrementCampagneException.class, () -> campagneService.save(campagneDto));
    }

    @Test
    public void testGetAllCampagnes() {
        // Création d'une liste de campagnes fictives pour simuler les données de la base de données
        List<Campagne> campagneList = Arrays.asList(
                Campagne.builder().id(1L).nom("Campagne 1").objectif("obj1").user(new User()).emplacement(new Emplacement()).build(),
                Campagne.builder().id(2L).nom("Campagne 2").objectif("obj2").user(new User()).emplacement(new Emplacement()).build()
        );

        // Définir le comportement du mock CampagneRepository lorsque la méthode findAll est appelée
        when(campagneRepository.findAll()).thenReturn(campagneList);

        // Appel de la méthode à tester du service CampagneService
        List<CampagneDto> campagneDtoList = campagneService.getAll();

        // Vérifier si la liste renvoyée correspond à ce à quoi vous vous attendez
        assertEquals(2, campagneDtoList.size());

        // Vérifier les détails de chaque campagne dans la liste
        assertEquals(1L, campagneDtoList.get(0).getId());
        assertEquals("Campagne 1", campagneDtoList.get(0).getNom());
        assertEquals(2L, campagneDtoList.get(1).getId());
        assertEquals("Campagne 2", campagneDtoList.get(1).getNom());
    }

    @Test
    public void testUpdateCampagne() {
        // Création d'une campagne fictive pour simuler les données de la base de données
        Campagne existingCampagne = new Campagne();
        existingCampagne.setId(1L);
        existingCampagne.setNom("Campagne 1");
        existingCampagne.setObjectif("Objectif initial");
        existingCampagne.setUser(new User());
        existingCampagne.setEmplacement(new Emplacement());

        // Campagne mise à jour fictive
        CampagneDto updatedCampagneDto = new CampagneDto();
        updatedCampagneDto.setNom("Campagne 1 - Mise à jour");
        updatedCampagneDto.setObjectif("Nouvel objectif");
        updatedCampagneDto.setUser(new UserDto());
        updatedCampagneDto.setEmplacement(new EmplacementDto());

        Campagne updatedCampagne = new Campagne();
        updatedCampagne.setNom("Campagne 1 - Mise à jour");
        updatedCampagne.setObjectif("Nouvel objectif");
        updatedCampagne.setUser(new User());
        updatedCampagne.setEmplacement(new Emplacement());

        // Définir le comportement du mock CampagneRepository lorsque la méthode findById est appelée
        when(campagneRepository.findById(1L)).thenReturn(Optional.of(existingCampagne));
        when(campagneRepository.save(Mockito.any(Campagne.class))).thenReturn(updatedCampagne);

        // Appel de la méthode à tester du service CampagneService
        CampagneDto returnedCampagneDto = campagneService.updateCampagne(1L, updatedCampagneDto);

        // Vérifier si la méthode findById a été appelée avec le bon ID
        verify(campagneRepository, times(1)).findById(1L);

        // Vérifier si les détails de la campagne mise à jour correspondent aux nouvelles données
        assertEquals(updatedCampagneDto.getNom(), existingCampagne.getNom());
        assertEquals(updatedCampagneDto.getBudget(), existingCampagne.getBudget());
        assertEquals(updatedCampagneDto.getDateDebut(), existingCampagne.getDateDebut());
        assertEquals(updatedCampagneDto.getDateFin(), existingCampagne.getDateFin());
        assertEquals(updatedCampagneDto.getObjectif(), existingCampagne.getObjectif());

        // Vérifier si l'objet CampagneDto renvoyé correspond aux détails de la campagne mise à jour
        assertEquals(updatedCampagneDto.getNom(), returnedCampagneDto.getNom());
        assertEquals(updatedCampagneDto.getBudget(), returnedCampagneDto.getBudget());
        assertEquals(updatedCampagneDto.getDateDebut(), returnedCampagneDto.getDateDebut());
        assertEquals(updatedCampagneDto.getDateFin(), returnedCampagneDto.getDateFin());
        assertEquals(updatedCampagneDto.getObjectif(), returnedCampagneDto.getObjectif());
    }

    @Test
    public void testUpdateCampagne_CampagneNotFound() {
        // Définir le comportement du mock CampagneRepository lorsque la méthode findById est appelée avec un ID inexistant
        when(campagneRepository.findById(1L)).thenReturn(Optional.empty());

        // Appel de la méthode à tester du service CampagneService et vérification si elle lance l'exception appropriée
        assertThrows(CampagneNonTrouveeException.class, () -> campagneService.updateCampagne(1L, new CampagneDto()));
    }

    @Test
    public void testDeleteCampagne_CampagneFound() {
        // Création d'une campagne fictive pour simuler la campagne récupérée par findById
        Campagne mockCampagne = new Campagne();
        mockCampagne.setId(1L);
        mockCampagne.setNom("Campagne 1");

        // Définir le comportement du mock CampagneRepository lorsque la méthode findById est appelée avec un ID valide
        when(campagneRepository.findById(1L)).thenReturn(Optional.of(mockCampagne));

        // Mocking de la méthode delete() du repository
        doNothing().when(campagneRepository).delete(mockCampagne);

        // Test
        campagneService.deleteCampagne(1L);

        // Vérifier si la méthode delete a été appelée avec la campagne récupérée
        verify(campagneRepository, times(1)).delete(mockCampagne);
    }

    @Test
    public void testDeleteCampagne_CampagneNotFound() {
        // Définir le comportement du mock CampagneRepository lorsque la méthode findById est appelée avec un ID non valide
        when(campagneRepository.findById(2L)).thenReturn(Optional.empty());

        // Test
        doThrow(RuntimeException.class).when(campagneRepository).delete(new Campagne());

        assertThrows(RuntimeException.class, () -> campagneService.deleteCampagne(2L));

        // then

        // Vérifier que la méthode delete n'est jamais appelée
        verify(campagneRepository, never()).delete(any());
    }
}
