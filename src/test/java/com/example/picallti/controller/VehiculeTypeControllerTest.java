package com.example.picallti.controller;

import com.example.picallti.model.Offre;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.service.OffreService;
import com.example.picallti.service.VehiculeTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VehiculeTypeControllerTest {

    @Mock
    private VehiculeTypeService vehiculeTypeService;

    @Autowired
    private VehiculeTypeController vehiculeTypeController;

    @BeforeEach
    void setUp(){
        vehiculeTypeController = new VehiculeTypeController();
        vehiculeTypeController.vehiculeTypeService = vehiculeTypeService;
    }

    @Test
    void getVehiculeType(){
        VehiculeType vehiculeType1 = new VehiculeType();
        VehiculeType vehiculeType2 = new VehiculeType();
        VehiculeType vehiculeType3 = new VehiculeType();
        List<VehiculeType> mockType = new ArrayList<>();
        mockType.addAll(List.of(vehiculeType1,vehiculeType2,vehiculeType3));
        when(vehiculeTypeService.getVehiculeType()).thenReturn(mockType);
        List<VehiculeType> vehiculeTypes = vehiculeTypeController.getVehiculeType();
        assertEquals(mockType,vehiculeTypes);
        verify(vehiculeTypeService).getVehiculeType();

    }

    @Test
    void getVehiculeTypeById(){
        int id = 1;
        VehiculeType mockVehiculeType = new VehiculeType(
                1,
                "Bicyclette",
                "url"
        );

        when(vehiculeTypeService.getVehiculeTypeById(id)).thenReturn(mockVehiculeType);
        VehiculeType vehiculeType = vehiculeTypeController.getVehiculeTypeById(id);
        assertEquals(mockVehiculeType,vehiculeType);
        verify(vehiculeTypeService).getVehiculeTypeById(id);


    }

}