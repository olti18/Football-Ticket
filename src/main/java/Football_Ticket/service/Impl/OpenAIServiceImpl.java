package Football_Ticket.service.Impl;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIServiceImpl {


//    @Value("${openai.api.key}")
//    private String apiKey;
//private String apiKey ="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";
//private String apiKey="";

    private static final String CHAT_API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String EMBEDDINGS_API_URL = "https://api.openai.com/v1/embeddings";

    private static final String[] matches = {
            "Barcelona vs Real Madrid - El Clasico",
            "Liverpool vs Manchester United - Premier League",
            "AC Milan vs Inter Milan - Serie A",
            "Argentina vs Brazil - World Cup Qualifier",
            "PSG vs Bayern Munich - Champions League"
    };

    private static final Map<String, double[]> matchEmbeddings = new HashMap<>();

    private final OkHttpClient client = new OkHttpClient();

//    public String getChatResponse(String userMessage) throws IOException {
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("model", "gpt-3.5-turbo");
//        jsonBody.put("messages", new JSONArray()
//                .put(new JSONObject().put("role", "system").put("content", "You are a football assistant."))
//                .put(new JSONObject().put("role", "user").put("content", userMessage)));
//
//        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json"));
//
//        Request request = new Request.Builder()
//                .url(CHAT_API_URL)
//                .header("Authorization", "Bearer " + apiKey)
//                .header("Content-Type", "application/json")
//                .post(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return new JSONObject(response.body().string())
//                .getJSONArray("choices")
//                .getJSONObject(0)
//                .getJSONObject("message")
//                .getString("content");
//    }


    public String getChatResponse(String userMessage) throws IOException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "gpt-3.5-turbo");

        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a football assistant."));
        messages.put(new JSONObject().put("role", "user").put("content", userMessage));

        jsonBody.put("messages", messages);
        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(CHAT_API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        // Print the full API response for debugging
        System.out.println("API Response: " + responseBody);

        // Check if the API response contains an error
        JSONObject jsonResponse = new JSONObject(responseBody);
        if (jsonResponse.has("error")) {
            JSONObject error = jsonResponse.getJSONObject("error");
            String errorMessage = error.getString("message");
            throw new IOException("API Error: " + errorMessage);
        }

        // Check if the API response is valid
        if (!jsonResponse.has("choices") || jsonResponse.getJSONArray("choices").isEmpty()) {
            throw new JSONException("Missing 'choices' field in response.");
        }

        return jsonResponse.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }






    private double[] getEmbedding(String text) throws IOException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "text-embedding-ada-002");
        jsonBody.put("input", text);

        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json"));

        Request request = new Request.Builder()
                .url(EMBEDDINGS_API_URL)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        // Kontrollo nëse JSON ka fushën "data"
        JSONObject jsonResponse = new JSONObject(responseBody);
        if (!jsonResponse.has("data")) {
            throw new JSONException("Missing 'data' field in response.");
        }

        JSONArray embeddingArray = jsonResponse.getJSONArray("data")
                .getJSONObject(0)
                .getJSONArray("embedding");

        double[] embedding = new double[embeddingArray.length()];
        for (int i = 0; i < embeddingArray.length(); i++) {
            embedding[i] = embeddingArray.getDouble(i);
        }
        return embedding;
    }


//    private double[] getEmbedding(String text) throws IOException {
//        JSONObject jsonBody = new JSONObject();
//        jsonBody.put("model", "text-embedding-ada-002");
//        jsonBody.put("input", text);
//
//        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json"));
//
//        Request request = new Request.Builder()
//                .url(EMBEDDINGS_API_URL)
//                .header("Authorization", "Bearer " + apiKey)
//                .header("Content-Type", "application/json")
//                .post(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//
//        JSONArray embeddingArray = new JSONObject(response.body().string())
//                .getJSONArray("data")
//                .getJSONObject(0)
//                .getJSONArray("embedding");
//
//        double[] embedding = new double[embeddingArray.length()];
//        for (int i = 0; i < embeddingArray.length(); i++) {
//            embedding[i] = embeddingArray.getDouble(i);
//        }
//        return embedding;
//    }

    public void generateMatchEmbeddings() throws IOException {
        for (String match : matches) {
            matchEmbeddings.put(match, getEmbedding(match));
        }
    }

    public String recommendMatch(String userPreference) throws IOException {
        double[] userEmbedding = getEmbedding(userPreference);

        double bestScore = -1;
        String bestMatch = "";
        for (Map.Entry<String, double[]> entry : matchEmbeddings.entrySet()) {
            double score = cosineSimilarity(userEmbedding, entry.getValue());
            if (score > bestScore) {
                bestScore = score;
                bestMatch = entry.getKey();
            }
        }
        return bestMatch;
    }

    private double cosineSimilarity(double[] vec1, double[] vec2) {
        double dotProduct = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < vec1.length; i++) {
            dotProduct += vec1[i] * vec2[i];
            normA += Math.pow(vec1[i], 2);
            normB += Math.pow(vec2[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

}
