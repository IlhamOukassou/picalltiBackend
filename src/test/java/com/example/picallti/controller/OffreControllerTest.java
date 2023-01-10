package com.example.picallti.controller;


import com.example.picallti.model.Offre;
import com.example.picallti.service.OffreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OffreController.class)
@ActiveProfiles("test")
class OffreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OffreService offreService;

    private List<Offre> offreList;

    @BeforeEach
    void setUp(){
        this.offreList = new ArrayList<>();
        this.offreList.add(new Offre(1,"Titre1", "The characteristics of someone or something","operation", 10.0f,"https://example.org/example",null,null, "rabat" ));
        this.offreList.add(new Offre(2,"Titre2", "The characteristics of someone or something","operation", 20.0f,"https://example.org/example",null,null, "rabat"));
        this.offreList.add(new Offre(3,"Titre3", "The characteristics of someone or something","operation", 30.0f,"https://example.org/example",null,null, "rabat"));
    }

    @Test
    void shouldFetchAllOffers() throws Exception{
        given(offreService.getAllOffers()).willReturn(offreList);
        this.mockMvc.perform(get("/offers/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(offreList.size())));
    }

    @Test
    void shouldFetchOneOfferById() throws Exception {
        final int offreId = 1;
        final Offre offre = new Offre(1,"Titre1", "The characteristics of someone or something","operation", 10.0f,"https://example.org/example",null,null, "rabat" );

        given(offreService.getOffreById(offreId)).willReturn(Optional.of(offre));
        this.mockMvc.perform(get("/offers/getById/{id}",offreId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre", is(offre.getTitre())))
                .andExpect(jsonPath("$.description", is(offre.getDescription())));
    }

    /*@Test
    void shouldDeleteOffre() throws Exception{
        int offreId = 1;
        final Offre offre = new Offre(1,"Titre1", "The characteristics of someone or something","operation", 10.0f,"https://example.org/example",null,null );

        given(offreService.getOffreById(offreId)).willReturn(offre);
        doNothing().when(offreService).removeById(offre.getId());

        this.mockMvc.perform(delete("/offers/remove/{id}", offre.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titre", is(offre.getTitre())))
                .andExpect(jsonPath("$.description", is(offre.getDescription())));
    }*/


}