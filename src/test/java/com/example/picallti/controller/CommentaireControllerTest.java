package com.example.picallti.controller;

import com.example.picallti.model.Commentaire;
import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.repository.CommentaireRepository;
import com.example.picallti.service.CommentaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CommentaireControllerTest {

    @Mock
    private CommentaireRepository commentaireRepository;

    @Mock
    private CommentaireService commentaireService;


    @Autowired
    private CommentaireController commentaireController;

    @BeforeEach
    void setUp(){
        commentaireController = new CommentaireController();
        commentaireController.commentaireService = commentaireService;
    }



    @Test
    void addCommentaire() {
        Commentaire mockCommentaire = new Commentaire();
        doNothing().when(commentaireService).addCommentaire(mockCommentaire);
        commentaireController.addCommentaire(mockCommentaire);
        verify(commentaireService).addCommentaire(mockCommentaire);


    }

    @Test
    void getAllCommentaires() {

        Commentaire commentaire1 = new Commentaire();
        Commentaire commentaire2 = new Commentaire();
        Collection<Commentaire> mockCommentaire = new ArrayList<>();
        mockCommentaire.addAll(List.of(commentaire1, commentaire2));
        when(commentaireService.getAllCommentaires()).thenReturn(mockCommentaire);
        Collection<Commentaire> Commentaires = commentaireController.getAllCommentaires();
        assertEquals(mockCommentaire,Commentaires);
        verify(commentaireService).getAllCommentaires();
    }

    @Test
    void getAllCommentairesByUser() {
        //User user = new User(1,"Nisrine","Mofakir","femme","nisb",0643,"");
       /* User user = new User();
        int idd = 1;
        user.setId(idd);
        Offre offre1 = new Offre();
        Commentaire mockCommentaire1 = new Commentaire(idd,"  ",user,offre1, "LocalTime.now().toString() ",LocalDateTime.now().toString());
        when(commentaireService.getCommentairesByUser(idd)).thenReturn(mockCommentaire1);
        Collection<Commentaire> commentaire = commentaireController.getAllCommentairesByUser(1);
        assertEquals(mockCommentaire1, commentaire);
        verify(commentaireService).getCommentairesByUser(1);*/

    }

    @Test
    void getAllCommentairesByOffre() {
        /*User user = new User();
        Offre offre1 = new Offre();
        Commentaire mockCommentaire1 = new Commentaire(1,"  ",user,offre1, "LocalTime.now().toString() ",LocalDateTime.now().toString());
        doReturn(mockCommentaire1).when(commentaireService.getCommentairesByOffre(1));
        Collection<Commentaire> commentaire = commentaireController.getAllCommentairesByOffre(1);

        assertEquals(mockCommentaire1, commentaire);
        verify(commentaireService).getCommentairesByOffre(1);*/
    }

    @Test
    void getCommentaireById() {
        User user = new User();
        Offre offre1 = new Offre();
        Commentaire mockCommentaire1 = new Commentaire(1,"  ",user,offre1, "LocalTime.now().toString() ",LocalDateTime.now().toString());
        when(commentaireService.getCommentaireById(1)).thenReturn(mockCommentaire1);
        Commentaire commentaire = commentaireController.getCommentaireById(1);

        assertEquals(mockCommentaire1, commentaire);
        verify(commentaireService).getCommentaireById(1);
    }

    @Test
    void updateCommentaire() {

        /*User user = new User();
        Offre offre1 = new Offre();
        Commentaire mockCommentaire1 = new Commentaire(1,"  ",user,offre1, "LocalTime.now().toString() ",LocalDateTime.now().toString());
        doReturn(mockCommentaire1).when(commentaireService.updateCommentaire(mockCommentaire1));
        Commentaire commentaire = commentaireController.updateCommentaire(mockCommentaire1);
        assertEquals(mockCommentaire1, commentaire);
        verify(commentaireService).updateCommentaire(commentaire);*/
    }

    @Test
    void removeCommentaireById() {
        User user = new User();
        Offre offre1 = new Offre();
        Commentaire mockCommentaire1 = new Commentaire(1,"  ",user,offre1, "LocalTime.now().toString() ",LocalDateTime.now().toString());

        doNothing().when(commentaireService).removeCommentaireById(1);
        commentaireController.removeCommentaireById(1);
        verify(commentaireService).removeCommentaireById(1);
    }
}