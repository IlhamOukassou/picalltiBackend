package com.example.picallti.controller;

import com.example.picallti.model.*;
import com.example.picallti.service.FavorisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(controllers = FavorisController.class)
@ActiveProfiles("test")
class FavorisControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FavorisService favorisService;

    @Autowired
    private FavorisController favorisController;
    @Autowired
    private ObjectMapper objectMapper;

    private List<Favoris> favorisList;
    private User userTest;


    @BeforeEach
    void setUp() {
        favorisController = new FavorisController(favorisService);
        this.favorisList = new ArrayList<>();
        //random object values
        VehiculeType vehiculetypeTest = new VehiculeType(1, "BMW");
        Vehicule vehiculeTest = new Vehicule("BMW", vehiculetypeTest);
        userTest = new User("Fadili", "Ayoub", "Male", "test@gmail.com", 660553514, "1234", 1, "test", "test");
        Offre offreTest = new Offre("test", "test", "test3", 10, "test", userTest, vehiculeTest);
        Offre offreTest2 = new Offre("test2", "test", "test3", 10, "test", userTest, vehiculeTest);
        Favoris testFavoris = new Favoris(1, userTest, offreTest);
        Favoris testFavoris2 = new Favoris(2, userTest, offreTest2);
        this.favorisList.add(testFavoris);
        this.favorisList.add(testFavoris2);


    }

    @Test
    void ShouldFetchAllFavoris() throws Exception {

        given(favorisService.findAllFavoris()).willReturn(favorisList);
        this.mockMvc.perform(get("/favoris/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(favorisList.size())));
    }

    @Test
    void shouldFetchOneFavorisById() throws Exception {
        final int favorisId = 1;
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        final Favoris testFavoris = new Favoris(favorisId, null, null, dateTime);


        given(favorisService.findFavorisById(favorisId)).willReturn(testFavoris);

        this.mockMvc.perform(get("/favoris/find/{id}", favorisId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testFavoris.getId())));
    }

    @Test
    void shouldCreateNewFavoris() throws Exception {
        given(favorisService.addFavoris(any(Favoris.class))).willAnswer((invocation) -> invocation.getArgument(0));

        final int favorisId = 1;
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        final Favoris testFavoris = new Favoris(favorisId, null, null, dateTime);

        this.mockMvc.perform(post("/favoris/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(testFavoris)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(testFavoris.getId())))
        ;

    }

    @Test
    void shouldDeleteFavoris() throws Exception {
        final int favorisId = 1;
        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        final Favoris testFavoris = new Favoris(favorisId, null, null, dateTime);
        doNothing().when(favorisService).deleteFavoris(testFavoris.getId());

        this.mockMvc.perform(delete("/favoris/delete/{id}", testFavoris.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFetchFavorisByUser() throws Exception {

        given(favorisService.findByUser(userTest.getId())).willReturn(Optional.ofNullable(favorisList));

        this.mockMvc.perform(get("/favoris/findallbyuser?id={id}", userTest.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(favorisList.size())));

    }
}

