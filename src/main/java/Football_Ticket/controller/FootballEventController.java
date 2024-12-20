package Football_Ticket.controller;

import Football_Ticket.model.FootballEvent;
import Football_Ticket.repository.FootballEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class FootballEventController {

    @Autowired
    private FootballEventRepository footballEventRepository;

    @GetMapping
    public List<FootballEvent> getAllEvents() {
        return footballEventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FootballEvent> getEventById(@PathVariable Long id) {
        return footballEventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FootballEvent createEvent(@RequestBody FootballEvent event) {
        return footballEventRepository.save(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FootballEvent> updateEvent(@PathVariable Long id, @RequestBody FootballEvent updatedEvent) {
        return footballEventRepository.findById(id)
                .map(event -> {
                    event.setName(updatedEvent.getName());
                    event.setDateTime(updatedEvent.getDateTime());
                    event.setStadium(updatedEvent.getStadium());
                    event.setAvailableTickets(updatedEvent.getAvailableTickets());
                    return ResponseEntity.ok(footballEventRepository.save(event));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        if (footballEventRepository.existsById(id)) {
            footballEventRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

