package com.example.picallti.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.picallti.model.Favoris;
import com.example.picallti.model.User;
import com.example.picallti.repository.FavorisRepository;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.FavorisService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

class FavorisControllerTest {
    /**
     * Method under test: {@link FavorisController#getAllFavoris()}
     */
    @Test
    void testGetAllFavoris() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Favoris>> actualAllFavoris = (new FavorisController(
                new FavorisService(favorisRepository, mock(UserRepository.class)))).getAllFavoris();
        assertTrue(actualAllFavoris.hasBody());
        assertEquals(200, actualAllFavoris.getStatusCodeValue());
        assertTrue(actualAllFavoris.getHeaders().isEmpty());
        verify(favorisRepository).findAll();
    }

    /**
     * Method under test: {@link FavorisController#getAllFavoris()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllFavoris2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.service.FavorisService.findAllFavoris()" because "this.favorisService" is null
        //       at com.example.picallti.controller.FavorisController.getAllFavoris(FavorisController.java:24)
        //   See https://diff.blue/R013 to resolve this issue.

        (new FavorisController(null)).getAllFavoris();
    }

    /**
     * Method under test: {@link FavorisController#getAllFavoris()}
     */
    @Test
    void testGetAllFavoris3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisService favorisService = mock(FavorisService.class);
        when(favorisService.findAllFavoris()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Favoris>> actualAllFavoris = (new FavorisController(favorisService)).getAllFavoris();
        assertTrue(actualAllFavoris.hasBody());
        assertEquals(200, actualAllFavoris.getStatusCodeValue());
        assertTrue(actualAllFavoris.getHeaders().isEmpty());
        verify(favorisService).findAllFavoris();
    }

    /**
     * Method under test: {@link FavorisController#getFavorisById(int)}
     */
    @Test
    void testGetFavorisById() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.of(new Favoris()));
        ResponseEntity<Favoris> actualFavorisById = (new FavorisController(
                new FavorisService(favorisRepository, mock(UserRepository.class)))).getFavorisById(1);
        assertTrue(actualFavorisById.hasBody());
        assertTrue(actualFavorisById.getHeaders().isEmpty());
        assertEquals(200, actualFavorisById.getStatusCodeValue());
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisController#getFavorisById(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetFavorisById2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.example.picallti.exception.FavorisNotFoundException: Favoris 1 not found !
        //       at com.example.picallti.service.FavorisService.lambda$findFavorisById$0(FavorisService.java:31)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.example.picallti.service.FavorisService.findFavorisById(FavorisService.java:31)
        //       at com.example.picallti.controller.FavorisController.getFavorisById(FavorisController.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.empty());
        (new FavorisController(new FavorisService(favorisRepository, mock(UserRepository.class)))).getFavorisById(1);
    }

    /**
     * Method under test: {@link FavorisController#getFavorisById(int)}
     */
    @Test
    void testGetFavorisById3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisService favorisService = mock(FavorisService.class);
        when(favorisService.findFavorisById(anyInt())).thenReturn(new Favoris());
        ResponseEntity<Favoris> actualFavorisById = (new FavorisController(favorisService)).getFavorisById(1);
        assertTrue(actualFavorisById.hasBody());
        assertTrue(actualFavorisById.getHeaders().isEmpty());
        assertEquals(200, actualFavorisById.getStatusCodeValue());
        verify(favorisService).findFavorisById(anyInt());
    }

    /**
     * Method under test: {@link FavorisController#getFavorisByUser(int)}
     */
    @Test
    void testGetFavorisByUser() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.findByUser((User) any())).thenReturn(Optional.of(new ArrayList<>()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(
                Optional.of(new User("Nom", "Prenom", "Genre", "jane.doe@example.org", 1, "iloveyou", 1, "Role")));
        ResponseEntity<Optional<List<Favoris>>> actualFavorisByUser = (new FavorisController(
                new FavorisService(favorisRepository, userRepository))).getFavorisByUser(1);
        assertTrue(actualFavorisByUser.getBody().isPresent());
        assertTrue(actualFavorisByUser.getHeaders().isEmpty());
        assertEquals(200, actualFavorisByUser.getStatusCodeValue());
        verify(favorisRepository).findByUser((User) any());
        verify(userRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisController#getFavorisByUser(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetFavorisByUser2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.example.picallti.service.FavorisService.findByUser(FavorisService.java:34)
        //       at com.example.picallti.controller.FavorisController.getFavorisByUser(FavorisController.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.findByUser((User) any())).thenReturn(Optional.of(new ArrayList<>()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById((Integer) any())).thenReturn(Optional.empty());
        (new FavorisController(new FavorisService(favorisRepository, userRepository))).getFavorisByUser(1);
    }

    /**
     * Method under test: {@link FavorisController#getFavorisByUser(int)}
     */
    @Test
    void testGetFavorisByUser3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisService favorisService = mock(FavorisService.class);
        when(favorisService.findByUser(anyInt())).thenReturn(Optional.of(new ArrayList<>()));
        ResponseEntity<Optional<List<Favoris>>> actualFavorisByUser = (new FavorisController(favorisService))
                .getFavorisByUser(1);
        assertTrue(actualFavorisByUser.getBody().isPresent());
        assertTrue(actualFavorisByUser.getHeaders().isEmpty());
        assertEquals(200, actualFavorisByUser.getStatusCodeValue());
        verify(favorisService).findByUser(anyInt());
    }

    /**
     * Method under test: {@link FavorisController#addFavoris(Favoris)}
     */
    @Test
    void testAddFavoris() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.picallti.model.Favoris["localDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.save((Favoris) any())).thenReturn(new Favoris());
        FavorisController favorisController = new FavorisController(
                new FavorisService(favorisRepository, mock(UserRepository.class)));
        ResponseEntity<Favoris> actualAddFavorisResult = favorisController.addFavoris(new Favoris());
        assertTrue(actualAddFavorisResult.hasBody());
        assertTrue(actualAddFavorisResult.getHeaders().isEmpty());
        assertEquals(201, actualAddFavorisResult.getStatusCodeValue());
        verify(favorisRepository).save((Favoris) any());
    }

    /**
     * Method under test: {@link FavorisController#addFavoris(Favoris)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddFavoris2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.picallti.model.Favoris["localDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.service.FavorisService.addFavoris(com.example.picallti.model.Favoris)" because "this.favorisService" is null
        //       at com.example.picallti.controller.FavorisController.addFavoris(FavorisController.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisController favorisController = new FavorisController(null);
        favorisController.addFavoris(new Favoris());
    }

    /**
     * Method under test: {@link FavorisController#addFavoris(Favoris)}
     */
    @Test
    void testAddFavoris3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.picallti.model.Favoris["localDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisService favorisService = mock(FavorisService.class);
        when(favorisService.addFavoris((Favoris) any())).thenReturn(new Favoris());
        FavorisController favorisController = new FavorisController(favorisService);
        ResponseEntity<Favoris> actualAddFavorisResult = favorisController.addFavoris(new Favoris());
        assertTrue(actualAddFavorisResult.hasBody());
        assertTrue(actualAddFavorisResult.getHeaders().isEmpty());
        assertEquals(201, actualAddFavorisResult.getStatusCodeValue());
        verify(favorisService).addFavoris((Favoris) any());
    }

    /**
     * Method under test: {@link FavorisController#updateFavoris(Favoris)}
     */
    @Test
    void testUpdateFavoris() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.picallti.model.Favoris["localDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.save((Favoris) any())).thenReturn(new Favoris());
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.of(new Favoris()));
        FavorisController favorisController = new FavorisController(
                new FavorisService(favorisRepository, mock(UserRepository.class)));
        ResponseEntity<Favoris> actualUpdateFavorisResult = favorisController.updateFavoris(new Favoris());
        assertTrue(actualUpdateFavorisResult.hasBody());
        assertTrue(actualUpdateFavorisResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateFavorisResult.getStatusCodeValue());
        verify(favorisRepository).save((Favoris) any());
        verify(favorisRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisController#updateFavoris(Favoris)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateFavoris2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.picallti.model.Favoris["localDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.example.picallti.service.FavorisService.updateFavoris(FavorisService.java:43)
        //       at com.example.picallti.controller.FavorisController.updateFavoris(FavorisController.java:48)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        when(favorisRepository.save((Favoris) any())).thenReturn(new Favoris());
        when(favorisRepository.findById((Integer) any())).thenReturn(Optional.empty());
        FavorisController favorisController = new FavorisController(
                new FavorisService(favorisRepository, mock(UserRepository.class)));
        favorisController.updateFavoris(new Favoris());
    }

    /**
     * Method under test: {@link FavorisController#updateFavoris(Favoris)}
     */
    @Test
    void testUpdateFavoris3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.picallti.model.Favoris["localDateTime"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1306)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:733)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:319)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4624)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3869)
        //   See https://diff.blue/R013 to resolve this issue.

        FavorisService favorisService = mock(FavorisService.class);
        when(favorisService.updateFavoris((Favoris) any())).thenReturn(new Favoris());
        FavorisController favorisController = new FavorisController(favorisService);
        ResponseEntity<Favoris> actualUpdateFavorisResult = favorisController.updateFavoris(new Favoris());
        assertTrue(actualUpdateFavorisResult.hasBody());
        assertTrue(actualUpdateFavorisResult.getHeaders().isEmpty());
        assertEquals(200, actualUpdateFavorisResult.getStatusCodeValue());
        verify(favorisService).updateFavoris((Favoris) any());
    }

    /**
     * Method under test: {@link FavorisController#deleteFavoris(int)}
     */
    @Test
    void testDeleteFavoris() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisRepository favorisRepository = mock(FavorisRepository.class);
        doNothing().when(favorisRepository).deleteById((Integer) any());
        ResponseEntity<?> actualDeleteFavorisResult = (new FavorisController(
                new FavorisService(favorisRepository, mock(UserRepository.class)))).deleteFavoris(1);
        assertNull(actualDeleteFavorisResult.getBody());
        assertEquals(200, actualDeleteFavorisResult.getStatusCodeValue());
        assertTrue(actualDeleteFavorisResult.getHeaders().isEmpty());
        verify(favorisRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link FavorisController#deleteFavoris(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteFavoris2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.picallti.service.FavorisService.deleteFavoris(int)" because "this.favorisService" is null
        //       at com.example.picallti.controller.FavorisController.deleteFavoris(FavorisController.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        (new FavorisController(null)).deleteFavoris(1);
    }

    /**
     * Method under test: {@link FavorisController#deleteFavoris(int)}
     */
    @Test
    void testDeleteFavoris3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        FavorisService favorisService = mock(FavorisService.class);
        doNothing().when(favorisService).deleteFavoris(anyInt());
        ResponseEntity<?> actualDeleteFavorisResult = (new FavorisController(favorisService)).deleteFavoris(1);
        assertNull(actualDeleteFavorisResult.getBody());
        assertEquals(200, actualDeleteFavorisResult.getStatusCodeValue());
        assertTrue(actualDeleteFavorisResult.getHeaders().isEmpty());
        verify(favorisService).deleteFavoris(anyInt());
    }
}

