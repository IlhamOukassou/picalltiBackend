package com.example.picallti.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.picallti.exception.FavorisNotFoundException;
import com.example.picallti.model.Favoris;
import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.repository.FavorisRepository;
import com.example.picallti.repository.UserRepository;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FavorisService.class})
@ExtendWith(SpringExtension.class)
class FavorisServiceTest {
    @MockBean
    private FavorisRepository favorisRepository;

    @Autowired
    private FavorisService favorisService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link FavorisService#addFavoris(Favoris)}
     */
    @Test
    void testAddFavoris() {
        Favoris favoris = new Favoris();
        when(favorisRepository.save((Favoris) any())).thenReturn(favoris);
        assertSame(favoris, favorisService.addFavoris(new Favoris()));
        verify(favorisRepository).save((Favoris) any());
    }

    /**
     * Method under test: {@link FavorisService#addFavoris(Favoris)}
     */
    @Test
    void testAddFavoris2() {
        when(favorisRepository.save((Favoris) any())).thenThrow(new FavorisNotFoundException("An error occurred"));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.addFavoris(new Favoris()));
        verify(favorisRepository).save((Favoris) any());
    }

    /**
     * Method under test: {@link FavorisService#findAllFavoris()}
     */
    @Test
    void testFindAllFavoris() {
        ArrayList<Favoris> favorisList = new ArrayList<>();
        when(favorisRepository.findAll()).thenReturn(favorisList);
        List<Favoris> actualFindAllFavorisResult = favorisService.findAllFavoris();
        assertSame(favorisList, actualFindAllFavorisResult);
        assertTrue(actualFindAllFavorisResult.isEmpty());
        verify(favorisRepository).findAll();
    }

    /**
     * Method under test: {@link FavorisService#findAllFavoris()}
     */
    @Test
    void testFindAllFavoris2() {
        when(favorisRepository.findAll()).thenThrow(new FavorisNotFoundException("An error occurred"));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.findAllFavoris());
        verify(favorisRepository).findAll();
    }

    /**
     * Method under test: {@link FavorisService#findFavorisById(int)}
     */
    @Test
    void testFindFavorisById() {
        Favoris favoris = new Favoris();
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.of(favoris));
        assertSame(favoris, favorisService.findFavorisById(1));
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#findFavorisById(int)}
     */
    @Test
    void testFindFavorisById2() {
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(FavorisNotFoundException.class, () -> favorisService.findFavorisById(1));
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#findFavorisById(int)}
     */
    @Test
    void testFindFavorisById3() {
        when(favorisRepository.findById((Integer) any())).thenThrow(new FavorisNotFoundException("An error occurred"));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.findFavorisById(1));
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#findByUser(int)}
     */
    @Test
    void testFindByUser() {
        Optional<List<Favoris>> ofResult = Optional.of(new ArrayList<>());
        when(favorisRepository.findByUser((User) any())).thenReturn(ofResult);
        when(userRepository.findById((Integer) any())).thenReturn(
                Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role")));
        Optional<List<Favoris>> actualFindByUserResult = favorisService.findByUser(1);
        assertSame(ofResult, actualFindByUserResult);
        assertTrue(actualFindByUserResult.isPresent());
        verify(favorisRepository).findByUser((User) any());
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#findByUser(int)}
     */
    @Test
    void testFindByUser2() {
        when(favorisRepository.findByUser((User) any())).thenThrow(new FavorisNotFoundException("An error occurred"));
        when(userRepository.findById((Integer) any())).thenReturn(
                Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", "img", "Role")));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.findByUser(1));
        verify(favorisRepository).findByUser((User) any());
        verify(userRepository).findById((Integer) any());
    }


    /**
     * Method under test: {@link FavorisService#updateFavoris(Favoris)}
     */
    @Test
    void testUpdateFavoris() {
        Favoris favoris = new Favoris();
        when(favorisRepository.save((Favoris) any())).thenReturn(favoris);
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.of(new Favoris()));
        assertSame(favoris, favorisService.updateFavoris(new Favoris()));
        verify(favorisRepository).save((Favoris) any());
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#updateFavoris(Favoris)}
     */
    @Test
    void testUpdateFavoris2() {
        when(favorisRepository.save((Favoris) any())).thenThrow(new FavorisNotFoundException("An error occurred"));
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.of(new Favoris()));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.updateFavoris(new Favoris()));
        verify(favorisRepository).save((Favoris) any());
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#updateFavoris(Favoris)}
     */
    @Test
    void testUpdateFavoris3() {
        Favoris favoris = mock(Favoris.class);
        when(favoris.getOffre()).thenThrow(new FavorisNotFoundException("An error occurred"));
        when(favoris.getUser()).thenThrow(new FavorisNotFoundException("An error occurred"));
        when(favoris.getlocaLDate()).thenThrow(new FavorisNotFoundException("An error occurred"));
        doThrow(new FavorisNotFoundException("An error occurred")).when(favoris).setLocalDateTime((LocalDateTime) any());
        doThrow(new FavorisNotFoundException("An error occurred")).when(favoris).setOffre((Offre) any());
        doThrow(new FavorisNotFoundException("An error occurred")).when(favoris).setUser((User) any());
        Optional<Favoris> ofResult = Optional.of(favoris);
        when(favorisRepository.save((Favoris) any())).thenReturn(new Favoris());
        when(favorisRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(FavorisNotFoundException.class, () -> favorisService.updateFavoris(new Favoris()));
        verify(favorisRepository).findById((Integer) any());
        verify(favoris).getUser();
    }



    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris() {
        when(favorisRepository.findByUser((User) any())).thenReturn(Optional.of(new ArrayList<>()));
        Favoris favoris = new Favoris();
        favorisService.deleteFavoris(favoris);
        verify(favorisRepository).findByUser((User) any());
        assertEquals(0, favoris.getId());
    }



    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris5() {
        when(favorisRepository.findByUser((User) any())).thenReturn(Optional.of(new ArrayList<>()));
        Favoris favoris = mock(Favoris.class);
        when(favoris.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        favorisService.deleteFavoris(favoris);
        verify(favorisRepository).findByUser((User) any());
        verify(favoris).getUser();
    }

    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris6() {
        ArrayList<Favoris> favorisList = new ArrayList<>();
        favorisList.add(new Favoris(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville")));
        Optional<List<Favoris>> ofResult = Optional.of(favorisList);
        doNothing().when(favorisRepository).deleteById((Integer) any());
        when(favorisRepository.findByUser((User) any())).thenReturn(ofResult);
        Favoris favoris = mock(Favoris.class);
        when(favoris.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));
        when(favoris.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        favorisService.deleteFavoris(favoris);
        verify(favorisRepository).findByUser((User) any());
        verify(favorisRepository).deleteById((Integer) any());
        verify(favoris).getOffre();
        verify(favoris).getUser();
    }

    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris7() {
        ArrayList<Favoris> favorisList = new ArrayList<>();
        favorisList.add(new Favoris(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville")));
        Optional<List<Favoris>> ofResult = Optional.of(favorisList);
        doThrow(new FavorisNotFoundException("An error occurred")).when(favorisRepository).deleteById((Integer) any());
        when(favorisRepository.findByUser((User) any())).thenReturn(ofResult);
        Favoris favoris = mock(Favoris.class);
        when(favoris.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));
        when(favoris.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.deleteFavoris(favoris));
        verify(favorisRepository).findByUser((User) any());
        verify(favorisRepository).deleteById((Integer) any());
        verify(favoris).getOffre();
        verify(favoris).getUser();
    }

    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris8() {
        Offre offre = mock(Offre.class);
        when(offre.getId()).thenReturn(1);
        Favoris e = new Favoris(offre);

        ArrayList<Favoris> favorisList = new ArrayList<>();
        favorisList.add(e);
        Optional<List<Favoris>> ofResult = Optional.of(favorisList);
        doNothing().when(favorisRepository).deleteById((Integer) any());
        when(favorisRepository.findByUser((User) any())).thenReturn(ofResult);
        Favoris favoris = mock(Favoris.class);
        when(favoris.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));
        when(favoris.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        favorisService.deleteFavoris(favoris);
        verify(favorisRepository).findByUser((User) any());
        verify(offre).getId();
        verify(favoris).getOffre();
        verify(favoris).getUser();
    }


    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris10() {
        Favoris favoris = mock(Favoris.class);
        when(favoris.getId()).thenReturn(1);
        when(favoris.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));

        ArrayList<Favoris> favorisList = new ArrayList<>();
        favorisList.add(favoris);
        Optional<List<Favoris>> ofResult = Optional.of(favorisList);
        doNothing().when(favorisRepository).deleteById((Integer) any());
        when(favorisRepository.findByUser((User) any())).thenReturn(ofResult);
        Favoris favoris1 = mock(Favoris.class);
        when(favoris1.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));
        when(favoris1.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        favorisService.deleteFavoris(favoris1);
        verify(favorisRepository).findByUser((User) any());
        verify(favorisRepository).deleteById((Integer) any());
        verify(favoris).getOffre();
        verify(favoris).getId();
        verify(favoris1).getOffre();
        verify(favoris1).getUser();
    }

    /**
     * Method under test: {@link FavorisService#deleteFavoris(Favoris)}
     */
    @Test
    void testDeleteFavoris11() {
        Favoris favoris = mock(Favoris.class);
        when(favoris.getId()).thenThrow(new FavorisNotFoundException("An error occurred"));
        when(favoris.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));

        ArrayList<Favoris> favorisList = new ArrayList<>();
        favorisList.add(favoris);
        Optional<List<Favoris>> ofResult = Optional.of(favorisList);
        doNothing().when(favorisRepository).deleteById((Integer) any());
        when(favorisRepository.findByUser((User) any())).thenReturn(ofResult);
        Favoris favoris1 = mock(Favoris.class);
        when(favoris1.getOffre()).thenReturn(new Offre("Titre", "The characteristics of someone or something", 10.0f,
                "https://example.org/example", "Ville"));
        when(favoris1.getUser()).thenReturn(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou"));
        assertThrows(FavorisNotFoundException.class, () -> favorisService.deleteFavoris(favoris1));
        verify(favorisRepository).findByUser((User) any());
        verify(favoris).getOffre();
        verify(favoris).getId();
        verify(favoris1).getOffre();
        verify(favoris1).getUser();
    }


}

