package com.example.picallti.controller;
import com.example.picallti.model.Commentaire;
import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import static org.mockito.Mockito.*;


import com.example.picallti.repository.CommentaireRepository;
import com.example.picallti.service.CommentaireService;

@ExtendWith(MockitoExtension.class)
public class CommentaireControllerTest {
    @Autowired
    private User user;
    @Autowired
    private Offre offre;
    @Mock
    private CommentaireRepository commentaireRepository;

    private CommentaireService commentaireService;

    private CommentaireController commentaireController;
    private AutoCloseable autoCloseable;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        commentaireController = new CommentaireController();
        commentaireService = new CommentaireService();
        commentaireService.commentaireRepository = commentaireRepository;
        commentaireController.commentaireService = commentaireService;
    }

    @AfterEach
    void tearDown() throws Exception {
        //autoCloseable.close();
    }

    @Test
    void canAddCommetaire() {
        //given
        Commentaire commentaire = new Commentaire(1, "Commentaire", user, offre, LocalTime.now(), LocalDate.now());
        //when
        commentaireController.addCommentaire(commentaire);
        //then
        ArgumentCaptor<Commentaire> argumentCaptor = ArgumentCaptor.forClass(Commentaire.class);
        verify(commentaireRepository).save(argumentCaptor.capture());

        Commentaire commentaire1 = argumentCaptor.getValue();
        assertThat(commentaire1).isEqualTo(commentaire);
    }


    @Test
    void canGetAllCommentaires() {
        //when
        commentaireController.getAllCommentaires();
        //then
        verify(commentaireRepository).findAll();


    }

    @Test
    void getCommentaireByIdIfExist() throws Exception {
        Commentaire mockCommentaire = new Commentaire();
        int commentaireId = 1;
        mockCommentaire.setId(commentaireId);
        lenient().when(commentaireRepository.existsById(commentaireId)).thenReturn(true);
        lenient().when(commentaireRepository.findById(commentaireId)).thenReturn(Optional.of(mockCommentaire));

        Commentaire commentaire = commentaireController.getCommentaireById(commentaireId);
        assertEquals(mockCommentaire, commentaire);


    }





    @Test
    void updateCommentaireIfExist() {
        int commentaireId = 1;

        Commentaire commentaire = new Commentaire(commentaireId, "commentaire", user, offre,LocalTime.now(), LocalDate.now());


        lenient().when(commentaireRepository.existsById(commentaireId)).thenReturn(true);
        lenient().when(commentaireRepository.findById(commentaireId)).thenReturn(Optional.of(commentaire));
        lenient().when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        Commentaire commentaire1 = commentaireController.updateCommentaire(commentaire);

        assertEquals(commentaire, commentaire1);

    }

    @Test
    void updateCommentaireIfNotExist() {
        int commentaireId = 1;
        Commentaire mockCommentaire = new Commentaire();
        mockCommentaire.setId(commentaireId);
        lenient().when(commentaireRepository.existsById(commentaireId)).thenReturn(true);
        Commentaire commentaire = null;
        assertThatExceptionOfType(RuntimeException.class);
    }
}
