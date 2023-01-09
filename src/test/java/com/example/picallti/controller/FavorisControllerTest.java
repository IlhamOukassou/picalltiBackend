package com.example.picallti.controller;

import com.example.picallti.model.*;
import com.example.picallti.service.FavorisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class FavorisControllerTest {


    @Mock
    private FavorisService favorisService;
    @Autowired
    private FavorisController favorisController;


    @BeforeEach
    void setUp() {
        favorisController = new FavorisController(favorisService);
    }

    @Test
    void getAllFavoris(){
        Favoris favoris1 = new Favoris();
        Favoris favoris2 = new Favoris();
        Favoris favoris3 = new Favoris();
        List<Favoris> mockFavoris = new ArrayList<>();
        mockFavoris.addAll(List.of(favoris1,favoris2,favoris3));
        ResponseEntity<List<Favoris>> mockFavorisEntity = new ResponseEntity<>(mockFavoris, HttpStatus.OK);
        when(favorisService.findAllFavoris()).thenReturn(mockFavoris);
        ResponseEntity<List<Favoris>> favoris = favorisController.getAllFavoris();
        assertEquals(mockFavorisEntity,favoris);
        verify(favorisService).findAllFavoris();
    }

    @Test
    void deleteFavoris(){
        Offre offre = new Offre();
        User user = new User();

        int favorisId = 1;
        Favoris mockFavoris = new Favoris(favorisId,user,offre);
        doNothing().when(favorisService).deleteFavoris(mockFavoris);
        favorisController.remove(mockFavoris);
        verify(favorisService).deleteFavoris(mockFavoris);
    }

    @Test
    void addFavoris(){
        Offre offre = new Offre();
        User user = new User();
        Favoris mockFavoris = new Favoris(1,user,offre);
        ResponseEntity<Favoris> mockFavorisEntity = new ResponseEntity<>(mockFavoris,HttpStatus.CREATED);
        when(favorisService.addFavoris(mockFavoris)).thenReturn(mockFavoris);
        ResponseEntity<Favoris> favoris = favorisController.addFavoris(mockFavoris);
        assertEquals(mockFavorisEntity,favoris);
        verify(favorisService).addFavoris(mockFavoris);
    }

    @Test
    void findAllFavorisByUser(){
        User user = new User();
        Offre offre = new Offre();
        Offre offre2 = new Offre();
        Favoris mockFavoris1 = new Favoris(1,user,offre);
        Favoris mockFavoris2 = new Favoris(2,user,offre2);
        List<Favoris> favorisList = new ArrayList<>();
        favorisList.addAll(List.of(mockFavoris1,mockFavoris2));
        ResponseEntity<Optional<List<Favoris>>> mockFavorisListEntity = new ResponseEntity<Optional<List<Favoris>>>(Optional.of(favorisList), HttpStatus.OK);
        when(favorisService.findByUser(user.getId())).thenReturn(Optional.of(favorisList));
        ResponseEntity<Optional<List<Favoris>>> favoris = favorisController.getFavorisByUser(user.getId());
        assertEquals(mockFavorisListEntity,favoris);
        verify(favorisService).findByUser(user.getId());
    }

}


