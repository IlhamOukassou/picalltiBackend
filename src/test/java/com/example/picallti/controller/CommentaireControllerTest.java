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
import static org.mockito.Mockito.mock;


import com.example.picallti.repository.CommentaireRepository;
import com.example.picallti.service.CommentaireService;

@ExtendWith(MockitoExtension.class)
public class CommentaireControllerTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    private CommentaireService commentaireService;

    private CommentaireController commentaireController;
    private AutoCloseable autoCloseable;
    private MockMvc mockMvc;

    /**
     * Method under test: {@link CommentaireController#addCommentaire(Commentaire)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCommentaire() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        CommentaireController commentaireController = new CommentaireController();
        commentaireController.addCommentaire(new Commentaire());
    }

    /**
     * Method under test: {@link CommentaireController#addCommentaire(Commentaire)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCommentaire2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).addCommentaire(mock(Commentaire.class));
    }

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
        User user1 = new User();
        Offre offre1 = new Offre();
        //given
        Commentaire commentaire = new Commentaire(1, "Commentaire", user1, offre1, LocalTime.now().toString(), LocalDate.now().toString());
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

    /**
     * Method under test: {@link CommentaireController#getAllCommentaires()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCommentaires() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).getAllCommentaires();
    }

    /**
     * Method under test: {@link CommentaireController#getAllCommentairesByUser(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCommentairesByUser() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).getAllCommentairesByUser(1);
    }

    /**
     * Method under test: {@link CommentaireController#getAllCommentairesByOffre(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllCommentairesByOffre() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).getAllCommentairesByOffre(1);
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

    /**
     * Method under test: {@link CommentaireController#getCommentaireById(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetCommentaireById() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).getCommentaireById(1);
    }

    /**
     * Method under test: {@link CommentaireController#updateCommentaire(Commentaire)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCommentaire() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        CommentaireController commentaireController = new CommentaireController();
        commentaireController.updateCommentaire(new Commentaire());
    }

    /**
     * Method under test: {@link CommentaireController#updateCommentaire(Commentaire)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCommentaire2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).updateCommentaire(mock(Commentaire.class));
    }


    @Test
    void updateCommentaireIfExist() {
        int commentaireId = 1;
        User user1 = new User();
        Offre offre1 = new Offre();
        Commentaire commentaire = new Commentaire(commentaireId, "commentaire", user1, offre1, LocalTime.now().toString(), LocalDate.now().toString());


        lenient().when(commentaireRepository.existsById(commentaireId)).thenReturn(true);
        lenient().when(commentaireRepository.findById(commentaireId)).thenReturn(Optional.of(commentaire));
        lenient().when(commentaireRepository.save(commentaire)).thenReturn(commentaire);

        Commentaire commentaire1 = commentaireController.updateCommentaire(commentaire);

        assertEquals(commentaire, commentaire1);

    }

    /**
     * Method under test: {@link CommentaireController#removeCommentaireById(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveCommentaireById() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new CommentaireController()).removeCommentaireById(1);
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
