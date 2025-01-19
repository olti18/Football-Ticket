package Football_Ticket.controller;

import Football_Ticket.service.Impl.AuthServiceImpl;
import jakarta.ws.rs.core.Response;
import org.apache.catalina.User;
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

import java.util.List;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthServiceImpl authService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {
        return authService.register(username, password, email);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam String username,
            @RequestParam String password) {
        return authService.login(username, password);
    }

//    @GetMapping
//    public ResponseEntity<List<UserRepresentation>> getAllUsers() {
//        List<UserRepresentation> users = authService.fetchAllUsers();
//        return ResponseEntity.ok(users);
//    }

    @GetMapping
    public ResponseEntity<List> getAllUsers() {
        List users = authService.fetchAllUsers().getBody();
        return ResponseEntity.ok(users);
    }

}