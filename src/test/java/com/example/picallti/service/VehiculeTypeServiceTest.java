package com.example.picallti.service;

import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.VehiculeTypeRepository;
import com.example.picallti.service.VehiculeTypeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {VehiculeTypeService.class})
@ExtendWith(SpringExtension.class)
class VehiculeTypeServiceTest {

    @MockBean
    private VehiculeTypeRepository vehiculeTypeRepository;

    @Autowired
    private VehiculeTypeService vehiculeTypeService;

    @Test
    void testGetVehiculeType() {
        ArrayList<VehiculeType> vehiculeTypesList = new ArrayList<>();
        when(vehiculeTypeRepository.findAll()).thenReturn(vehiculeTypesList);
        Collection<VehiculeType> actualAllVehiculeType = vehiculeTypeService.getVehiculeType();
        assertSame(vehiculeTypesList, actualAllVehiculeType);
        assertTrue(actualAllVehiculeType.isEmpty());
        verify(vehiculeTypeRepository).findAll();
    }
    @Test
    void testGetVehiculeTypeById() {
        Optional<VehiculeType> ofResult = Optional
                .of(new VehiculeType(1, "Name", "URL"));
        when(vehiculeTypeRepository.findById((Integer) any())).thenReturn(ofResult);
        Optional<VehiculeType> actualVehiculeTypeById = vehiculeTypeService.getVehiculeTypeById(1);
        assertSame(ofResult, actualVehiculeTypeById);
        verify(vehiculeTypeRepository).findById((Integer) any());
    }
    @Test
    void testGetUserByEmail() {
        Optional<VehiculeType> ofResult = Optional
                .of(new VehiculeType(1, "Name", "URL"));
        when(vehiculeTypeRepository.findVehiculeTypeByName((String) any())).thenReturn(ofResult);
        Optional<VehiculeType> actualVehiculeTypeByName = vehiculeTypeService.getVehiculeTypeByName("Bicyclette");
        assertSame(ofResult, actualVehiculeTypeByName);
        assertTrue(actualVehiculeTypeByName.isPresent());
        verify(vehiculeTypeRepository).findVehiculeTypeByName((String) any());
    }

}
