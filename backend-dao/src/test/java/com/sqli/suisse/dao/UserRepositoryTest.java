package com.sqli.suisse.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import com.sqli.suisse.dao.model.User;
import com.sqli.suisse.dao.repository.UserRepository;

@ContextConfiguration(classes = TestRepositoryConfig.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateReadDelete() {
        User user = new User("wadoud", "abdel", "test");

        userRepository.save(user);

        Iterable<User> users = userRepository.findAll();
        Assertions.assertThat(users).extracting(User::getFirstName).contains("abdel", "toto");

        userRepository.deleteAll();
        Assertions.assertThat(userRepository.findAll()).isEmpty();
    }

}
