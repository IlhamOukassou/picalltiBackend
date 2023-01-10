package com.example.picallti.controller;

import com.example.picallti.model.Vehicule;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeRepository;
import com.example.picallti.service.VehiculeService;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {VehiculeController.class})
@ExtendWith(MockitoExtension.class)
class VehiculeControllerTest {

    @Mock
    private VehiculeRepository vehiculeRepository;
    @Mock
    private VehiculeService vehiculeService;
    private AutoCloseable autoCloseable;
    private VehiculeController vehiculeController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        vehiculeController = new VehiculeController();
        vehiculeController.vehiculeService = vehiculeService;
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    /**
     * Method under test: {@link VehiculeController#addVehicule(Vehicule)}
     */
    @Test
    void testAddVehicule() {
        Vehicule vehicule = new Vehicule();
        VehiculeService vehiculeService = mock(VehiculeService.class);
        doNothing().when(vehiculeService).addVehicule((Vehicule) any());
        VehiculeController vehiculeController = new VehiculeController();
        vehiculeController.vehiculeService = vehiculeService;
        vehiculeController.addVehicule(new Vehicule("Marque"));
        verify(vehiculeService).addVehicule((Vehicule) any());
    }


    @Test
    void getAllvehicules() {
//        vehiculeService.getAllVehicules();
//        verify(vehiculeRepository).findAll();
        Vehicule vehicule1= new Vehicule();
        Vehicule vehicule2= new Vehicule();
        Vehicule vehicule3= new Vehicule();
        List<Vehicule> MockVehicles = new ArrayList<>();
        MockVehicles.addAll(List.of(vehicule1,vehicule2,vehicule3));
        when(vehiculeService.getAllVehicules()).thenReturn(MockVehicles);

        Collection<Vehicule> vehicules= vehiculeController.getAllvehicules();
        assertEquals(MockVehicles, vehicules);
        verify(vehiculeService).getAllVehicules();

    }


    @Test
    void removeVehicule() {
        int id = 1;
        VehiculeType vehiculeType = new VehiculeType(2, "nom", "url");
        Vehicule vehicule = new Vehicule(id, "marque", vehiculeType);
        doNothing().when(vehiculeService).removeVehiculeById(id);
        vehiculeController.removeVehicule(id);
        verify(vehiculeService).removeVehiculeById(id);

    }
}