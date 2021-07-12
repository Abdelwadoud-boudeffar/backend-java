package com.sqli.suisse;

import com.sqli.suisse.dao.model.User;
import com.sqli.suisse.dao.repository.UserRepository;
import com.sqli.suisse.dto.UserCreateDTO;
import com.sqli.suisse.dto.UserViewDTO;
import com.sqli.suisse.service.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    @DisplayName("Test findById Success")
    public void testFindById() {
        // Setup our mock repository
        User user = new User("abdel", "wadoud", "test");
        doReturn(Optional.of(user)).when(userRepository).findById(1l);

        // Execute the service call
        Optional<UserViewDTO> returnedUser = Optional.ofNullable(userService.getUserById(1l));

        // Assert the response
        Assertions.assertTrue(returnedUser.isPresent(), "User was not found");
    }

    @Test
    @DisplayName("Test save user")
    public void testSave() {
        // Setup our mock repository
        User user = new User("abdel", "wadoud", "test");
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setLastName("test");
        userCreateDTO.setFirstName("wadoud");
        userCreateDTO.setUserName("abdel");

        doReturn(user).when(userRepository).save(any());

        // Execute the service call
        UserViewDTO returnedUser = userService.createUser(userCreateDTO);

        // Assert the response
        Assertions.assertNotNull(returnedUser, "The saved user should not be null");
    }

    @Test
    @DisplayName("Test findAll")
    public void testFindAll() {
        // Setup our mock repository
        User user = new User("abdel", "wadoud", "test");
        User user2 = new User("abdel2", "wadoud2", "test2");
        doReturn(Arrays.asList(user, user2)).when(userRepository).findAll();

        // Execute the service call
        List<UserViewDTO> users = userService.getUsers();

        // Assert the response
        Assertions.assertEquals(2, users.size(), "findAll should return 2 users");
    }

}
