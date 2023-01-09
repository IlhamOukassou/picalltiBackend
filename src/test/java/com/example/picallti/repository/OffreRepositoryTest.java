package com.example.picallti.repository;

import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.model.Vehicule;
import com.example.picallti.model.VehiculeType;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {OffreRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.picallti.model"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class OffreRepositoryTest {
    @Autowired
    private OffreRepository offreRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private VehiculeRepository vehiculeRepository;

//    @Test
//    public void testFilterOffresByPrix() {
//
//        float min = 10;
//        float max = 20;
//        Collection<Offre> result = offreRepository.filterOffresByPrix(min, max);
//
//        User user = new User();
//        userRepository.save(user);
//        Vehicule vehicule = new Vehicule();
//        vehiculeRepository.save(vehicule);
//        Offre offre1 = new Offre(1, 1,  "titre",  "description",  "localisation",
//                15, "time",  "operation",  user, vehicule, "locaLDate", "ville");
//        Offre offre2 = new Offre(1, 1,  "titre",  "description",  "localisation",
//                15, "time",  "operation",  user, vehicule, "locaLDate", "ville");
//        offreRepository.save(offre1);
//        offreRepository.save(offre2);
//
//        ArrayList<Offre> expectedResult = new ArrayList() {{
//            add(offre1);
//            add(offre2);
//        }};
//
//        assertEquals(expectedResult, result);
//    }
}

