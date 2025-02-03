package Football_Ticket.controller;

import Football_Ticket.service.Impl.OpenAIServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
@RestController
public class OpenAIController {

    private final OpenAIServiceImpl openAIService;

    public OpenAIController(OpenAIServiceImpl openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/chat")
    public Map<String, String> chat(@RequestParam String message) throws IOException {
        String response = openAIService.getChatResponse(message);
        return Map.of("response", response);
    }

    @GetMapping("/recommend")
    public Map<String, String> recommend(@RequestParam String preference) throws IOException {
        openAIService.generateMatchEmbeddings(); // Inicimi i embeddings
        String recommendation = openAIService.recommendMatch(preference);
        return Map.of("recommendedMatch", recommendation);
    }

}
