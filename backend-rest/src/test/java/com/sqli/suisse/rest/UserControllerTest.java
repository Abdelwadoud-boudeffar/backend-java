package com.sqli.suisse.rest;

import com.sqli.suisse.dto.UserCreateDTO;
import com.sqli.suisse.dto.UserViewDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @DisplayName("Test Get User")
    @Test
    void testGet() throws MalformedURLException {
        UserViewDTO userViewDTOExpected = new UserViewDTO();
        userViewDTOExpected.setFirstName("Abdel1");
        ResponseEntity<UserViewDTO> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/api/v1/user/1").toString(), UserViewDTO.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(userViewDTOExpected.getFirstName(), response.getBody().getFirstName());
    }

    @DisplayName("Test Create User")
    @Test
    void testCreate() throws URISyntaxException {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setFirstName("test1");
        userCreateDTO.setLastName("test2");
        userCreateDTO.setUserName("test");

        final String baseUrl = "http://localhost:"+port+"/api/v1/user";
        URI uri = new URI(baseUrl);
        HttpEntity<UserCreateDTO> request = new HttpEntity<>(userCreateDTO);
        ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(true, response.getBody().contains("User Created."));
    }

    @DisplayName("Test Create User with error")
    @Test
    public void testCreateUserMissingUserName() throws URISyntaxException {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setFirstName("test1");
        userCreateDTO.setLastName("test2");

        final String baseUrl = "http://localhost:"+port+"/api/v1/user";
        URI uri = new URI(baseUrl);
        HttpEntity<UserCreateDTO> request = new HttpEntity<>(userCreateDTO);
        ResponseEntity<String> response = this.restTemplate.postForEntity(uri, request, String.class);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals(true, response.getBody().contains("User Name cannot be null"));
    }
}
