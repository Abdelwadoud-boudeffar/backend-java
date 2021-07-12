package com.sqli.suisse.rest;

import com.sqli.suisse.dto.UserViewDTO;
import com.sqli.suisse.rest.controller.UserController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class BaseTestClass {

    @Mock
    private UserController userController;

    @Before
    public void setup() {
        List<UserViewDTO> users = Arrays.asList(
                new UserViewDTO("Abdel1", "Boudeffar1"),
                new UserViewDTO("Abdel2", "Boudeffar2"),
                new UserViewDTO("Abdel3", "Boudeffar3")
        );
        doReturn(ResponseEntity.ok(users)).when(userController).getUsers();
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(userController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
