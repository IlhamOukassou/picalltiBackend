package com.example.picallti.service;

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
     * Method under test: {@link FavorisService#findByUser(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByUser3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.picallti.repository.UserRepository.findById(Object)" is null
        //       at com.example.picallti.service.FavorisService.findByUser(FavorisService.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        when(favorisRepository.findByUser((User) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(userRepository.findById((Integer) any())).thenReturn(null);
        favorisService.findByUser(1);
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
     * Method under test: {@link FavorisService#updateFavoris(Favoris)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateFavoris4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.get()" because the return value of "com.example.picallti.repository.FavorisRepository.findById(Object)" is null
        //       at com.example.picallti.service.FavorisService.updateFavoris(FavorisService.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        when(favorisRepository.save((Favoris) any())).thenReturn(new Favoris());
        when(favorisRepository.findById((Integer) any())).thenReturn(null);
        new FavorisNotFoundException("An error occurred");
        new FavorisNotFoundException("An error occurred");
        new FavorisNotFoundException("An error occurred");
        new FavorisNotFoundException("An error occurred");
        new FavorisNotFoundException("An error occurred");
        new FavorisNotFoundException("An error occurred");
        favorisService.updateFavoris(new Favoris());
    }

    /**
     * Method under test: {@link FavorisService#updateFavoris(Favoris)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateFavoris5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.model.Favoris.getId()" because "favoris" is null
        //       at com.example.picallti.service.FavorisService.updateFavoris(FavorisService.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

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
        favorisService.updateFavoris(null);
    }

    /**
     * Method under test: {@link FavorisService#deleteFavoris(int)}
     */
    @Test
    void testDeleteFavoris() {
        doNothing().when(favorisRepository).deleteById((Integer) any());
        favorisService.deleteFavoris(1);
        verify(favorisRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisService#deleteFavoris(int)}
     */
    @Test
    void testDeleteFavoris2() {
        doThrow(new FavorisNotFoundException("An error occurred")).when(favorisRepository).deleteById((Integer) any());
        assertThrows(FavorisNotFoundException.class, () -> favorisService.deleteFavoris(1));
        verify(favorisRepository).deleteById((Integer) any());
    }
}

