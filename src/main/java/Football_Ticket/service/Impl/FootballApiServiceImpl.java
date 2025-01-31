package Football_Ticket.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class FootballApiServiceImpl {

    private final WebClient webClient;
    private final ObjectMapper objectMapper; // Declare ObjectMappe
    public FootballApiServiceImpl(WebClient webClient) {
        this.webClient = webClient;
        this.objectMapper = new ObjectMapper();
    }

    public String FriendlyMatches() {
        String response = webClient.get()
                .uri("/fixtures?league=10&season=2023")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("API Response: " + response); // Debugging line

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode fixtures = root.get("response");

            List<JsonNode> upcomingMatches = new ArrayList<>();
            LocalDateTime now = LocalDateTime.now();

            for (JsonNode match : fixtures) {
                String dateString = match.get("fixture").get("date").asText();
                ZonedDateTime matchDate = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);

                if (matchDate.isBefore(ZonedDateTime.now())) { // Filtrimi i ndeshjeve që kanë përfunduar
                    upcomingMatches.add(match);
                }
            }
            return objectMapper.writeValueAsString(upcomingMatches);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to parse API response: " + e.getMessage() + "\"}";
        }

    }

    public String getPremierLeagueMatches() {
        String response = webClient.get()
                .uri("/fixtures?league=39&season=2023")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("API Response: " + response); // Debugging

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode fixtures = root.get("response");
            List<JsonNode> filteredMatches = new ArrayList<>();
            ZonedDateTime now = ZonedDateTime.now();

            for (JsonNode match : fixtures) {
                String dateString = match.get("fixture").get("date").asText();
                ZonedDateTime matchDate = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
                String matchStatus = match.get("fixture").get("status").get("short").asText();

                // Filtro ndeshjet e ardhshme ose të përfunduara
                if (matchStatus.equals("FT") || matchDate.isAfter(now)) {
                    filteredMatches.add(match);
                }
            }
            return objectMapper.writeValueAsString(filteredMatches);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\": \"Failed to parse API response: " + e.getMessage() + "\"}";
        }
    }




//    public String getUpcomingFriendlyMatches() {
//        return webClient.get()
//                .uri("/fixtures?league=10&season=2023&next=10") // Fetch next 10 fixtures
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }


//public String getUpcomingFriendlyMatches() {
//    String response = webClient.get()
//            .uri("/fixtures?league=10&season=2023") // Fetch all fixtures
//            .retrieve()
//            .bodyToMono(String.class)
//            .block();
//
//    try {
//        JsonNode root = objectMapper.readTree(response);
//        JsonNode fixtures = root.get("response");
//        List<JsonNode> upcomingMatches = new ArrayList<>();
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
//
//        for (JsonNode match : fixtures) {
//            String dateString = match.get("fixture").get("date").asText();
//            LocalDateTime matchDate = LocalDateTime.parse(dateString, formatter);
//
//            if (matchDate.isAfter(now)) { // Filter future matches
//                upcomingMatches.add(match);
//            }
//        }
//        return objectMapper.writeValueAsString(upcomingMatches);
//    } catch (Exception e) {
//        return "{\"error\": \"Failed to parse API response\"}";
//    }
//}

//    public String getFriendlyMatches() {
//        return webClient.get()
//                .uri("/fixtures?league=10&season=2023&live=all") // Use correct league ID
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }
//
//    public String getKosovoMatches() {
//        return webClient.get()
//            .uri("/fixtures?league=303&season=2023&live=all")
//            .retrieve()
//            .bodyToMono(String.class)
//            .block(); // Blocking call (use only for small requests)
//    }

}
