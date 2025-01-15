package Football_Ticket.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> register(String username, String password, String email);
    ResponseEntity<?> login(String username, String password);

}
