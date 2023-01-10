package com.example.picallti.service;

import com.example.picallti.model.Offre;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.repository.OffreRepository;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.repository.VehiculeTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void testgetVehiculeType() {
        ArrayList<VehiculeType> vehiculeTypeList = new ArrayList<>();
        when(vehiculeTypeRepository.findAll()).thenReturn(vehiculeTypeList);
        List<VehiculeType> actualAllVehiculeType = vehiculeTypeService.getVehiculeType();
        assertSame(vehiculeTypeList, actualAllVehiculeType);
        assertTrue(actualAllVehiculeType.isEmpty());
        verify(vehiculeTypeRepository).findAll();
    }

    @Test
    void testGetOffreById() {
        VehiculeType vehiculeType = new VehiculeType(1,"bicyclette1", "url1");

        when(vehiculeTypeRepository.findById((Integer) any())).thenReturn(Optional.of(vehiculeType));
        assertSame(vehiculeType, vehiculeTypeService.getVehiculeTypeById(1));
        verify(vehiculeTypeRepository).findById((Integer) any());
    }

    @Test
    void testGetUserByName() {
        Optional<VehiculeType> ofResult = Optional
                .of(new VehiculeType(1, "Name", "URL"));
        when(vehiculeTypeRepository.findVehiculeTypeByName((String) any())).thenReturn(ofResult);
        Optional<VehiculeType> actualVehiculeTypeByName = vehiculeTypeService.getVehiculeTypeByName("Bicyclette");
        assertSame(ofResult, actualVehiculeTypeByName);
        assertTrue(actualVehiculeTypeByName.isPresent());
        verify(vehiculeTypeRepository).findVehiculeTypeByName((String) any());
    }




}