package com.example.picallti.controller;


import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.service.OffreService;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OffreControllerTest {


    @Mock
    private OffreService offreService;
    @Autowired
    private OffreController offreController;


    @BeforeEach
    void setUp() {
        offreController = new OffreController(offreService);
    }

    @Test
    void getAllOffres(){
        Offre offre1 = new Offre();
        Offre offre2 = new Offre();
        Collection<Offre> mockOffres = new ArrayList<>();
        mockOffres.addAll(List.of(offre1,offre2));
        when(offreService.getAllOffers()).thenReturn(mockOffres);
        Collection<Offre> Offres = offreController.getAllOffers();
        assertEquals(mockOffres,Offres);
        verify(offreService).getAllOffers();
    }

    @Test
    void addOffre(){
        Offre mockOffre = new Offre();
        doNothing().when(offreService).addOffre(mockOffre);
        offreController.addOffre(mockOffre);
        verify(offreService).addOffre(mockOffre);
    }
    @Test
    void removeOffre(){
        int offreId = 1;
        Offre mockOffre = new Offre("titre","desc",10,"ville");
        mockOffre.setId(offreId);
        doNothing().when(offreService).removeById(offreId);
        offreController.removeOffreById(offreId);
        verify(offreService).removeById(offreId);
    }
    @Test
    void getAllOffresByUser(){
        int userId = 1;
        User user = new User();
        user.setId(userId);
        Offre offre = new Offre(1,"titre","desc","op",123,user,null,"ville");
        Offre offre2 = new Offre(2,"titre","desc","op",123,user,null,"ville");
        Collection<Offre> mockOffres = new ArrayList<>();
        mockOffres.addAll(List.of(offre,offre2));
        when(offreService.getOffersByUser(userId)).thenReturn(mockOffres);
        Collection<Offre> offres = offreController.getAllOffersByUser(userId);
        assertEquals(mockOffres,offres);
        verify(offreService).getOffersByUser(userId);
    }
    @Test
    void getTitleContaining(){
        String titre = "titre";
        Offre offre = new Offre();
        Offre offre2 = new Offre();
        offre.setTitre(titre);
        offre2.setTitre(titre);
        Collection<Offre> mockOffres = new ArrayList<>();
        mockOffres.addAll(List.of(offre,offre2));
        when(offreService.findInTitleLike(titre)).thenReturn(mockOffres);
        Collection<Offre> offres = offreController.searchTitleContaining(titre);
        assertEquals(mockOffres,offres);
        verify(offreService).findInTitleLike(titre);
    }




}