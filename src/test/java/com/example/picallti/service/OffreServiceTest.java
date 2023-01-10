package com.example.picallti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.model.Vehicule;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.OffreRepository;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.repository.VehiculeTypeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OffreService.class})
@ExtendWith(SpringExtension.class)
class OffreServiceTest {
    @MockBean
    private OffreRepository offreRepository;

    @Autowired
    private OffreService offreService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VehiculeTypeRepository vehiculeTypeRepository;

    /**
     * Method under test: {@link OffreService#addOffre(Offre)}
     */
    @Test
    void testAddOffre() {
        when(offreRepository.save((Offre) any()))
                .thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        Offre offre = new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville");

        offreService.addOffre(offre);
        verify(offreRepository).save((Offre) any());
        assertEquals("The characteristics of someone or something", offre.getDescription());
        assertEquals("Ville", offre.getVille());
        assertEquals("Titre", offre.getTitre());
        assertEquals(10.0f, offre.getPrix());
        assertTrue(offreService.findByDateDesc().isEmpty());
    }

    /**
     * Method under test: {@link OffreService#getAllOffers()}
     */
    @Test
    void testGetAllOffers() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findAll()).thenReturn(offreList);
        Collection<Offre> actualAllOffers = offreService.getAllOffers();
        assertSame(offreList, actualAllOffers);
        assertTrue(actualAllOffers.isEmpty());
        verify(offreRepository).findAll();
    }

    /**
     * Method under test: {@link OffreService#getOffreById(int)}
     */
    @Test
    void testGetOffreById() {
        Optional<Offre> ofResult = Optional
                .of(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        when(offreRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<Offre> actualOffreById = offreService.getOffreById(1);
        assertSame(ofResult, actualOffreById);
        assertTrue(actualOffreById.isPresent());
        verify(offreRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OffreService#getOffersByUser(int)}
     */
    @Test
    void testGetOffersByUser() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findOffresByUser((User) any())).thenReturn(offreList);
        when(userRepository.findById((Integer) any()))
                .thenReturn(Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou")));
        Collection<Offre> actualOffersByUser = offreService.getOffersByUser(1);
        assertSame(offreList, actualOffersByUser);
        assertTrue(actualOffersByUser.isEmpty());
        verify(offreRepository).findOffresByUser((User) any());
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OffreService#getOffersByUser(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetOffersByUser2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.picallti.repository.UserRepository.findById(Object)" is null
        //       at com.example.picallti.service.OffreService.getOffersByUser(OffreService.java:45)
        //   See https://diff.blue/R013 to resolve this issue.

        when(offreRepository.findOffresByUser((User) any())).thenReturn(new ArrayList<>());
        when(userRepository.findById((Integer) any())).thenReturn(null);
        offreService.getOffersByUser(1);
    }

    /**
     * Method under test: {@link OffreService#getOffersByVehiculeType(String)}
     */
    @Test
    void testGetOffersByVehiculeType() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findOffresByVehiculeVehiculeType((VehiculeType) any())).thenReturn(offreList);
        when(vehiculeTypeRepository.findVehiculeTypeByName((String) any()))
                .thenReturn(Optional.of(new VehiculeType("Name")));
        Collection<Offre> actualOffersByVehiculeType = offreService.getOffersByVehiculeType("Vehicule Type Name");
        assertSame(offreList, actualOffersByVehiculeType);
        assertTrue(actualOffersByVehiculeType.isEmpty());
        verify(offreRepository).findOffresByVehiculeVehiculeType((VehiculeType) any());
        verify(vehiculeTypeRepository).findVehiculeTypeByName((String) any());
    }

    /**
     * Method under test: {@link OffreService#getOffersByVehiculeType(String)}
     */
    @Test
    void testGetOffersByVehiculeType2() {
        when(offreRepository.findOffresByVehiculeVehiculeType((VehiculeType) any())).thenReturn(new ArrayList<>());
        when(vehiculeTypeRepository.findVehiculeTypeByName((String) any())).thenReturn(Optional.empty());
        assertTrue(offreService.getOffersByVehiculeType("Vehicule Type Name").isEmpty());
        verify(vehiculeTypeRepository).findVehiculeTypeByName((String) any());
    }

    /**
     * Method under test: {@link OffreService#updateOffre(Offre)}
     */
    @Test
    void testUpdateOffre() {
        when(offreRepository.save((Offre) any()))
                .thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        when(offreRepository.findById((Integer) any()))
                .thenReturn(Optional.of(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville")));
        offreService.updateOffre(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        verify(offreRepository).save((Offre) any());
        verify(offreRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OffreService#updateOffre(Offre)}
     */
    @Test
    void testUpdateOffre2() {
        Offre offre = mock(Offre.class);
        doNothing().when(offre).setDescription((String) any());
        doNothing().when(offre).setImageId((Long) any());
        doNothing().when(offre).setOperation((String) any());
        doNothing().when(offre).setPrix(anyFloat());
        doNothing().when(offre).setTime((String) any());
        doNothing().when(offre).setTitre((String) any());
        doNothing().when(offre).setUser((User) any());
        doNothing().when(offre).setVehicule((Vehicule) any());
        doNothing().when(offre).setlocaLDate((String) any());
        Optional<Offre> ofResult = Optional.of(offre);
        when(offreRepository.save((Offre) any()))
                .thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        when(offreRepository.findById((Integer) any())).thenReturn(ofResult);
        offreService.updateOffre(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        verify(offreRepository).save((Offre) any());
        verify(offreRepository).findById((Integer) any());
        verify(offre).setDescription((String) any());
        verify(offre).setImageId((Long) any());
        verify(offre).setOperation((String) any());
        verify(offre).setPrix(anyFloat());
        verify(offre).setTime((String) any());
        verify(offre).setTitre((String) any());
        verify(offre).setUser((User) any());
        verify(offre).setVehicule((Vehicule) any());
        verify(offre).setlocaLDate((String) any());
    }

    /**
     * Method under test: {@link OffreService#updateOffre(Offre)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOffre3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.picallti.repository.OffreRepository.findById(Object)" is null
        //       at com.example.picallti.service.OffreService.updateOffre(OffreService.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        when(offreRepository.save((Offre) any()))
                .thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        when(offreRepository.findById((Integer) any())).thenReturn(null);
        offreService.updateOffre(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
    }

    /**
     * Method under test: {@link OffreService#updateOffre(Offre)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateOffre4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.model.Offre.getId()" because "offre" is null
        //       at com.example.picallti.service.OffreService.updateOffre(OffreService.java:67)
        //   See https://diff.blue/R013 to resolve this issue.

        Offre offre = mock(Offre.class);
        doNothing().when(offre).setDescription((String) any());
        doNothing().when(offre).setImageId((Long) any());
        doNothing().when(offre).setOperation((String) any());
        doNothing().when(offre).setPrix(anyFloat());
        doNothing().when(offre).setTime((String) any());
        doNothing().when(offre).setTitre((String) any());
        doNothing().when(offre).setUser((User) any());
        doNothing().when(offre).setVehicule((Vehicule) any());
        doNothing().when(offre).setlocaLDate((String) any());
        Optional<Offre> ofResult = Optional.of(offre);
        when(offreRepository.save((Offre) any()))
                .thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        when(offreRepository.findById((Integer) any())).thenReturn(ofResult);
        offreService.updateOffre(null);
    }

    /**
     * Method under test: {@link OffreService#updateOffre(Offre)}
     */
    @Test
    void testUpdateOffre5() {
        Offre offre = mock(Offre.class);
        doNothing().when(offre).setDescription((String) any());
        doNothing().when(offre).setImageId((Long) any());
        doNothing().when(offre).setOperation((String) any());
        doNothing().when(offre).setPrix(anyFloat());
        doNothing().when(offre).setTime((String) any());
        doNothing().when(offre).setTitre((String) any());
        doNothing().when(offre).setUser((User) any());
        doNothing().when(offre).setVehicule((Vehicule) any());
        doNothing().when(offre).setlocaLDate((String) any());
        Optional<Offre> ofResult = Optional.of(offre);
        when(offreRepository.save((Offre) any()))
                .thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f, "Ville"));
        when(offreRepository.findById((Integer) any())).thenReturn(ofResult);
        Offre offre1 = mock(Offre.class);
        when(offre1.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        when(offre1.getVehicule()).thenReturn(new Vehicule("Marque"));
        when(offre1.getPrix()).thenReturn(10.0f);
        when(offre1.getId()).thenReturn(1);
        when(offre1.getImageId()).thenReturn(123L);
        when(offre1.getDescription()).thenReturn("The characteristics of someone or something");
        when(offre1.getOperation()).thenReturn("Operation");
        when(offre1.getTime()).thenReturn("Time");
        when(offre1.getTitre()).thenReturn("Titre");
        when(offre1.getlocaLDate()).thenReturn("2020-03-01");
        offreService.updateOffre(offre1);
        verify(offreRepository).save((Offre) any());
        verify(offreRepository).findById((Integer) any());
        verify(offre).setDescription((String) any());
        verify(offre).setImageId((Long) any());
        verify(offre).setOperation((String) any());
        verify(offre).setPrix(anyFloat());
        verify(offre).setTime((String) any());
        verify(offre).setTitre((String) any());
        verify(offre).setUser((User) any());
        verify(offre).setVehicule((Vehicule) any());
        verify(offre).setlocaLDate((String) any());
        verify(offre1).getUser();
        verify(offre1).getVehicule();
        verify(offre1).getPrix();
        verify(offre1).getId();
        verify(offre1).getImageId();
        verify(offre1).getDescription();
        verify(offre1).getOperation();
        verify(offre1).getTime();
        verify(offre1).getTitre();
        verify(offre1).getlocaLDate();
    }

    /**
     * Method under test: {@link OffreService#removeById(int)}
     */
    @Test
    void testRemoveById() {
        doNothing().when(offreRepository).deleteById((Integer) any());
        offreService.removeById(1);
        verify(offreRepository).deleteById((Integer) any());
        assertTrue(offreService.findByDateDesc().isEmpty());
    }

    /**
     * Method under test: {@link OffreService#findByDateDesc()}
     */
    @Test
    void testFindByDateDesc() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findByDateDesc()).thenReturn(offreList);
        Collection<Offre> actualFindByDateDescResult = offreService.findByDateDesc();
        assertSame(offreList, actualFindByDateDescResult);
        assertTrue(actualFindByDateDescResult.isEmpty());
        verify(offreRepository).findByDateDesc();
    }

    /**
     * Method under test: {@link OffreService#findOffreByLocaLDate(String)}
     */
    @Test
    void testFindOffreByLocaLDate() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findOffreByLocaLDate((String) any())).thenReturn(offreList);
        Collection<Offre> actualFindOffreByLocaLDateResult = offreService.findOffreByLocaLDate("2020-03-01");
        assertSame(offreList, actualFindOffreByLocaLDateResult);
        assertTrue(actualFindOffreByLocaLDateResult.isEmpty());
        verify(offreRepository).findOffreByLocaLDate((String) any());
    }

    /**
     * Method under test: {@link OffreService#findOffreByVille(String)}
     */
    @Test
    void testFindOffreByVille() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findOffreByVille((String) any())).thenReturn(offreList);
        Collection<Offre> actualFindOffreByVilleResult = offreService.findOffreByVille("Ville");
        assertSame(offreList, actualFindOffreByVilleResult);
        assertTrue(actualFindOffreByVilleResult.isEmpty());
        verify(offreRepository).findOffreByVille((String) any());
    }

    /**
     * Method under test: {@link OffreService#filterOffresByPrix(float, float)}
     */
    @Test
    void testFilterOffresByPrix() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.filterOffresByPrix(anyFloat(), anyFloat())).thenReturn(offreList);
        Collection<Offre> actualFilterOffresByPrixResult = offreService.filterOffresByPrix(10.0f, 10.0f);
        assertSame(offreList, actualFilterOffresByPrixResult);
        assertTrue(actualFilterOffresByPrixResult.isEmpty());
        verify(offreRepository).filterOffresByPrix(anyFloat(), anyFloat());
    }

    /**
     * Method under test: {@link OffreService#findInTitleLike(String)}
     */
    @Test
    void testFindInTitleLike() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.getOffreByTitreContainingIgnoreCase((String) any())).thenReturn(offreList);
        Collection<Offre> actualFindInTitleLikeResult = offreService.findInTitleLike("Titre");
        assertSame(offreList, actualFindInTitleLikeResult);
        assertTrue(actualFindInTitleLikeResult.isEmpty());
        verify(offreRepository).getOffreByTitreContainingIgnoreCase((String) any());
    }
}

