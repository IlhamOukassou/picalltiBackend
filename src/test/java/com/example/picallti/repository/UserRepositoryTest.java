package com.example.picallti.repository;

import com.example.picallti.repository.UserRepository;
import com.example.picallti.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void findByEmail() {
    }

    @Test
    void existsUserByEmail() {
        //given
        String email = "malak@gmail.com";
        User user = new User(1,"nom","prenom", "f",
                "email@gmail.com", 0655446622, "password",
                "imageName", "bio", "role");
        userRepository.save(user);
        //when
        boolean expected = userRepository.existsUserByEmail(email);
        //then
        assertThat(expected).isTrue();
    }

    @Test
    void findByPhone() {
    }

    @Test
    void findOneByEmailIgnoreCaseAndPassword() {
    }
}