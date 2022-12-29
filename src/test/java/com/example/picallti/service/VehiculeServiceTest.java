package com.example.picallti.service;

import com.example.picallti.model.User;
import com.example.picallti.model.Vehicule;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {VehiculeService.class})
@ExtendWith(SpringExtension.class)
class VehiculeServiceTest {

    @MockBean
    private VehiculeRepository vehiculeRepository;
    @Autowired
    private VehiculeService vehiculeService;


    @AfterEach
    void tearDown() throws Exception{
    }

    @Test
    void testAddVehicule() {

        VehiculeType vehiculeType = new VehiculeType(1,"nom", "url");
        Vehicule vehicule = new Vehicule("marque", vehiculeType );
        vehiculeService.addVehicule(vehicule);
        verify(vehiculeRepository).save((Vehicule) any());
        assertEquals("marque", vehicule.getMarque());
        assertEquals(vehiculeType, vehicule.getVehiculeType());
        assertTrue(vehiculeService.getAllVehicules().isEmpty());
    }

    @Test
    void testGetAllVehicules() {
        ArrayList<Vehicule> vehiculesList = new ArrayList<>();
        when(vehiculeRepository.findAll()).thenReturn(vehiculesList);
        Collection<Vehicule> actualVehicles = vehiculeService.getAllVehicules();
        assertSame(vehiculesList, actualVehicles);
        assertTrue(actualVehicles.isEmpty());
        verify(vehiculeRepository).findAll();
    }
    @Test
    void removeVehiculeById() {
        int id = 1;
        given(vehiculeRepository.existsById(id)).willReturn(false);
        vehiculeService.removeVehiculeById(id);
        verify(vehiculeRepository).deleteById(id);
    }
}