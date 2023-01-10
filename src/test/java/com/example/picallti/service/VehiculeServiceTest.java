package com.example.picallti.service;

import com.example.picallti.model.Vehicule;
import com.example.picallti.repository.VehiculeRepository;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {VehiculeService.class})
@ExtendWith(SpringExtension.class)
class VehiculeServiceTest {

    @Autowired
    VehiculeService vehiculeService;

    @MockBean
    VehiculeRepository vehiculeRepository;

    @Test
    public void testAddVehicule() {
        // Arrange
        Vehicule vehicule = new Vehicule();
        // Act
        vehiculeService.addVehicule(vehicule);
        // Assert
        verify(vehiculeRepository, times(1)).save(vehicule);
    }

    /**
     * Method under test: {@link VehiculeService#addVehicule(Vehicule)}
     */
    @Test
    void testAddVehicule2() {
        when(vehiculeRepository.save((Vehicule) any())).thenReturn(new Vehicule("Marque"));
        Vehicule vehicule = new Vehicule("Marque");
        vehiculeService.addVehicule(vehicule);
        verify(vehiculeRepository).save((Vehicule) any());
        assertEquals(0, vehicule.getId());
        assertEquals("Marque", vehicule.getMarque());
    }

    /**
     * Method under test: {@link VehiculeService#getAllVehicules()}
     */
    @Test
    void testGetAllVehicules() {
        ArrayList<Vehicule> vehiculeList = new ArrayList<>();
        when(vehiculeRepository.findAll()).thenReturn(vehiculeList);
        Collection<Vehicule> actualAllVehicules = vehiculeService.getAllVehicules();
        assertSame(vehiculeList, actualAllVehicules);
        assertTrue(actualAllVehicules.isEmpty());
        verify(vehiculeRepository).findAll();
    }

    /**
     * Method under test: {@link VehiculeService#getLast()}
     */
    @Test
    void testGetLast() {
        Vehicule vehicule = new Vehicule("Marque");
        when(vehiculeRepository.findTopByOrderByIdDesc()).thenReturn(vehicule);
        assertSame(vehicule, vehiculeService.getLast());
        verify(vehiculeRepository).findTopByOrderByIdDesc();
    }

    /**
     * Method under test: {@link VehiculeService#removeVehiculeById(int)}
     */
    @Test
    void testRemoveVehiculeById() {
        doNothing().when(vehiculeRepository).deleteById((Integer) any());
        vehiculeService.removeVehiculeById(1);
        verify(vehiculeRepository).deleteById((Integer) any());
    }

}