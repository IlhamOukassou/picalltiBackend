package com.example.picallti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.repository.OffreRepository;
import com.example.picallti.repository.UserRepository;

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

    /**
     * Method under test: {@link OffreService#addOffre(Offre)}
     */
    @Test
    void testAddOffre() {
        when(offreRepository.save((Offre) any())).thenReturn(
                new Offre("Titre", "The characteristics of someone or something", 10.0f, "https://example.org/example", "rabat"));
        Offre offre = new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "rabat");

        offreService.addOffre(offre);
        verify(offreRepository).save((Offre) any());
        assertEquals("The characteristics of someone or something", offre.getDescription());
        assertEquals("https://example.org/example", offre.getUrl());
        assertEquals("Titre", offre.getTitre());
        assertEquals(10.0f, offre.getPrix());
        assertTrue(offreService.getAllOffers().isEmpty());
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
        Offre offre = new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "rabat");

        when(offreRepository.findById((Integer) any())).thenReturn(Optional.of(offre));
        assertSame(offre, offreService.getOffreById(1));
        verify(offreRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OffreService#getOffersByUser(int)}
     */
    @Test
    void testGetOffersByUser() {
        ArrayList<Offre> offreList = new ArrayList<>();
        when(offreRepository.findOffresByUser((User) any())).thenReturn(offreList);
        when(userRepository.findById((Integer) any())).thenReturn(
                Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", 1, "Role")));
        Collection<Offre> actualOffersByUser = offreService.getOffersByUser(1);
        assertSame(offreList, actualOffersByUser);
        assertTrue(actualOffersByUser.isEmpty());
        verify(offreRepository).findOffresByUser((User) any());
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OffreService#updateOffre(Offre)}
     */
    @Test
    void testUpdateOffre() {
        when(offreRepository.save((Offre) any())).thenReturn(
                new Offre("Titre", "The characteristics of someone or something", 10.0f, "https://example.org/example", "rabat"));
        when(offreRepository.findById((Integer) any())).thenReturn(Optional
                .of(new Offre("Titre", "The characteristics of someone or something", 10.0f, "https://example.org/example", "rabat")));
        offreService.updateOffre(
                new Offre("Titre", "The characteristics of someone or something", 10.0f, "https://example.org/example", "rabat"));
        verify(offreRepository).save((Offre) any());
        verify(offreRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link OffreService#removeById(int)}
     */
    @Test
    void testRemoveById() {
        doNothing().when(offreRepository).deleteById((Integer) any());
        offreService.removeById(1);
        verify(offreRepository).deleteById((Integer) any());
        assertTrue(offreService.getAllOffers().isEmpty());
    }
}

