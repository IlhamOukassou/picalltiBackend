package com.example.picallti.controller;

import com.example.picallti.model.Offre;
import com.example.picallti.model.VehiculeType;
import com.example.picallti.service.OffreService;
import com.example.picallti.service.VehiculeTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = VehiculeTypeController.class)
@ActiveProfiles("test")
class VehiculeTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehiculeTypeService vehiculeTypeService;

    private List<VehiculeType> vehiculeTypeList;

    @BeforeEach
    void setUp(){
        this.vehiculeTypeList = new ArrayList<>();
        this.vehiculeTypeList.add(new VehiculeType(1,"bicyclette1", "url1"));
        this.vehiculeTypeList.add(new VehiculeType(2,"bicyclette2", "url2"));
        this.vehiculeTypeList.add(new VehiculeType(3,"bicyclette3", "url3"));

    }

    @Test
    void shouldFetchAllVehiculeType() throws Exception{
        given(vehiculeTypeService.getVehiculeType()).willReturn(vehiculeTypeList);
        this.mockMvc.perform(get("/vehiculetype/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(vehiculeTypeList.size())));

    }

    @Test
    void shouldFetchOneVehiculeTypeById() throws Exception {
        final int vehiculeTypeId = 1;
        final VehiculeType vehiculeType = new VehiculeType(1,"bicyclette1", "url1");

        given(vehiculeTypeService.getVehiculeTypeById(vehiculeTypeId)).willReturn(vehiculeType);
        this.mockMvc.perform(get("/vehiculetype/getbyid",vehiculeTypeId ))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(vehiculeType.getname())))
                .andExpect(jsonPath("$.url", is(vehiculeType.getUrl())));
    }

}