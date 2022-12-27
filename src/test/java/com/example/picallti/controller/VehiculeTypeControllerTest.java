package com.example.picallti.controller;

import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeTypeRepository;
import com.example.picallti.service.VehiculeTypeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VehiculeTypeControllerTest {

    @Mock
    private VehiculeTypeRepository vehiculeTypeRepository;
    private VehiculeTypeService vehiculeTypeService;
    private VehiculeTypeController vehiculeTypeController;
    private AutoCloseable autoCloseable;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        vehiculeTypeController = new VehiculeTypeController();
        vehiculeTypeService = new VehiculeTypeService();
        vehiculeTypeService.vehiculeTypeRepository = vehiculeTypeRepository;
        vehiculeTypeController.vehiculeTypeService = vehiculeTypeService;
    }

    @AfterEach
    void tearDown() throws Exception {
        //autoCloseable.close();
    }
    @Test
    void canGetVehiculeType() {
        //when
        vehiculeTypeController.getVehiculeType();
        //then
        verify(vehiculeTypeRepository).findAll();


    }
    @Test
    void getVehiculeTypeByIdIfNotExist() throws Exception {
        int vehiculeTypeId = 1;
        lenient().when(vehiculeTypeRepository.existsById(vehiculeTypeId)).thenReturn(false);
        VehiculeType vehiculeType = vehiculeTypeController.getVehiculeTypeById(vehiculeTypeId);
        assertNull(vehiculeType);

    }
    @Test
    void getVehiculeTypeByIdIfExist() throws Exception {
        VehiculeType mockVehiculeType = new VehiculeType();
        int vehiculeTypeId = 1;
        mockVehiculeType.setId(vehiculeTypeId);
        lenient().when(vehiculeTypeRepository.existsById(vehiculeTypeId)).thenReturn(true);
        lenient().when(vehiculeTypeRepository.findById(vehiculeTypeId)).thenReturn(Optional.of(mockVehiculeType));
        VehiculeType vehiculeType = vehiculeTypeController.getVehiculeTypeById(vehiculeTypeId);
        assertEquals(mockVehiculeType, vehiculeType);
    }
    @Test
    void getVehiculeTypeByNameIfNotExist() {
        String name = "name";
        lenient().when(vehiculeTypeRepository.existsVehiculeTypeByName(name)).thenReturn(false);
        VehiculeType vehiculeType = vehiculeTypeController.getVehiculeTypeByName(name);
        assertNull(vehiculeType);
    }
    @Test
    void getVehiculeTypeByNameIfExist() {
        VehiculeType mockVehiculeType = new VehiculeType();
        String name = "name";
        mockVehiculeType.setname(name);
        lenient().when(vehiculeTypeRepository.existsVehiculeTypeByName(name)).thenReturn(true);
        lenient().when(vehiculeTypeRepository.findVehiculeTypeByName(name)).thenReturn(Optional.of(mockVehiculeType));
        VehiculeType vehiculeType= vehiculeTypeController.getVehiculeTypeByName(name);
        System.out.println(vehiculeType);
        assertEquals(mockVehiculeType, vehiculeType);
    }


}