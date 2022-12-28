package com.example.picallti.controller;

import com.example.picallti.model.User;
import com.example.picallti.model.Vehicule;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeRepository;
import com.example.picallti.service.UserService;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
@ContextConfiguration(classes = {VehiculeController.class})
@ExtendWith(MockitoExtension.class)
class VehiculeControllerTest {

    @Autowired
    private VehiculeController vehiculeController;
    @Mock
    private VehiculeRepository vehiculeRepository ;

    @Mock
    private VehiculeService vehiculeService ;



    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        vehiculeService = new VehiculeService();
        vehiculeService.vehiculeRepository = vehiculeRepository;
        vehiculeController= new VehiculeController();
        vehiculeController.vehiculeService = vehiculeService;
    }

    @AfterEach
    void tearDown() throws Exception  {
    }

    @Test
    void testAddVehicule() {
        //given
        VehiculeType vehiculeType = new VehiculeType(1,"nom", "url");
        Vehicule vehicule = new Vehicule("marque", vehiculeType );
        //when
        vehiculeController.addVehicule(vehicule);
        //then
        ArgumentCaptor<Vehicule> vehiculeArgumentCaptor = ArgumentCaptor.forClass(Vehicule.class);
        verify(vehiculeRepository).save(vehiculeArgumentCaptor.capture());
        Vehicule captureStudent = vehiculeArgumentCaptor.getValue();
        assertThat(captureStudent).isEqualTo(vehicule);
    }

    @Test
    void testGetAllvehicules() {
        //when
        vehiculeController.getAllvehicules();
        //then
        verify(vehiculeRepository).findAll();
    }

    @Test
    void testRemoveVehicule() {
        VehiculeType vehiculeType = new VehiculeType(2,"nom", "url");
        Vehicule vehicule = new Vehicule(1, "marque", vehiculeType);
        //given(vehiculeRepository.existsById(1)).willReturn(false);
        vehiculeController.removeVehicule(1);
        verify(vehiculeRepository).deleteById(1);

    }
}