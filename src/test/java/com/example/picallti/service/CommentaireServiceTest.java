package com.example.picallti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.picallti.model.Commentaire;
import com.example.picallti.repository.CommentaireRepository;

@ContextConfiguration(classes = {CommentaireService.class})
@ExtendWith(SpringExtension.class)
public class CommentaireServiceTest {
    @MockBean
    private CommentaireRepository commentaireRepository;

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private User user;

    @Autowired
    private Offre offre;

    /**
     * Method under test: {@link CommentaireService#addCommentaire(Commentaire)}
     */
    @Test
    void testAddCommentaire() {
        when(commentaireRepository.save((Commentaire) any()))
                .thenReturn(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
        Commentaire commentaire = new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now());

        commentaireService.addCommentaire(commentaire);
        verify(commentaireRepository).save((Commentaire) any());
        assertEquals("Commentaire", commentaire.getCommentaire());
        assertEquals(user, commentaire.getUser());
        assertEquals(offre, commentaire.getOffre());
        assertEquals(LocalTime.now(), commentaire.getTime());
        assertEquals(LocalDate.now(), commentaire.getLocalDateTime());
        assertEquals(0, commentaire.getId());
        assertTrue(commentaireService.getAllCommentaires().isEmpty());
    }

    /**
     * Method under test: {@link CommentaireService#getAllCommentaires()}
     */
    @Test
    void testGetAllCommentaires() {
        ArrayList<Commentaire> commentaireList = new ArrayList<>();
        when(commentaireRepository.findAll()).thenReturn(commentaireList);
        Collection<Commentaire> actualAllCommentaires = commentaireService.getAllCommentaires();
        assertSame(commentaireList, actualAllCommentaires);
        assertTrue(actualAllCommentaires.isEmpty());
        verify(commentaireRepository).findAll();
    }

    /**
     * Method under test: {@link CommentaireService#getCommentaireById(int)}
     */
    @Test
    void testGetCommentaireById() {
        Optional<Commentaire> ofResult = Optional
                .of(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
        when(commentaireRepository.findById((Integer) any())).thenReturn(ofResult);
        Commentaire actualCommentaireById = commentaireService.getCommentaireById(1);
        assertSame(ofResult, actualCommentaireById);
        verify(commentaireRepository).findById((Integer) any());
    }



    /**
     * Method under test: {@link CommentaireService#updateCommentaire(Commentaire)}
     */
    @Test
    void testUpdateCommentaire() {
        when(commentaireRepository.save((Commentaire) any()))
                .thenReturn(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
        when(commentaireRepository.findById((Integer) any())).thenReturn(
                Optional.of(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now())));
        commentaireService.updateCommentaire(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
        verify(commentaireRepository).save((Commentaire) any());
        verify(commentaireRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link CommentaireService#updateCommentaire(Commentaire)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.picallti.repository.CommentaireRepository.findById(Object)" is null
        //       at com.example.picallti.service.CommentaireService.updateCommentaire(CommentaireService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        when(commentaireRepository.save((Commentaire) any()))
                .thenReturn(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
        when(commentaireRepository.findById((Integer) any())).thenReturn(null);
        commentaireService.updateCommentaire(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
    }

    /**
     * Method under test: {@link CommentaireService#updateCommentaire(Commentaire)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdate3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.model.Commentaire.getId()" because "commentaire" is null
        //       at com.example.picallti.service.CommentaireService.updateCommentaire(CommentaireService.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        when(commentaireRepository.save((Commentaire) any()))
                .thenReturn(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now()));
        when(commentaireRepository.findById((Integer) any())).thenReturn(
                Optional.of(new Commentaire("Commentaire", user, offre, LocalTime.now(), LocalDate.now())));
        commentaireService.updateCommentaire(null);
    }

    /**
     * Method under test: {@link CommentaireService#removeCommentaireById(int)}
     */
    @Test
    void testRemoveCommentaireById() {
        doNothing().when(commentaireRepository).deleteById((Integer) any());
        commentaireService.removeCommentaireById(1);
        verify(commentaireRepository).deleteById((Integer) any());
        assertTrue(commentaireService.getAllCommentaires().isEmpty());
    }
}
