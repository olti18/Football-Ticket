package Football_Ticket.controller;

import Football_Ticket.service.Impl.AuthServiceImpl;
import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthServiceImpl authService;

    /**
     * Endpoint for user registration.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param email the email of the user.
     * @return a ResponseEntity indicating the result of the registration.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        return authService.register(username, password, email);
    }

    /**
     * Endpoint for user login.
     * @param username the username of the user.
     * @param password the password of the user.
     * @return a ResponseEntity containing the login result (access token).
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam String username,
            @RequestParam String password) {
        return authService.login(username, password);
    }

}