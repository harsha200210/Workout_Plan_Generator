package lk.ijse.workoutplanbackend.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GeminiAiUtil {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    public String[] generateResponse(String prompt) {
        String url = GEMINI_API_URL + apiKey;

        // Correct JSON format for Gemini API
        Map<String, Object> requestBody = new HashMap<>();
        List<Map<String, Object>> contents = new ArrayList<>();
        Map<String, Object> content = new HashMap<>();
        List<Map<String, String>> parts = new ArrayList<>();
        Map<String, String> part = new HashMap<>();

        part.put("text", prompt);
        parts.add(part);
        content.put("parts", parts);
        contents.add(content);
        requestBody.put("contents", contents);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        String jsonResponse = response.getBody();

        try {
            // Initialize Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse JSON response
            JsonNode rootNode = objectMapper.readTree(jsonResponse);

            // Navigate to candidates[0].content.parts[0].text
            String textResponse = rootNode.path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text")
                    .asText();

            // Print extracted text
            System.out.println("Extracted Exercises:");
            System.out.println(textResponse);

            // Split by newline if you need separate exercises
            String[] exercises = textResponse.split("\n");
            for (String exercise : exercises) {
                if (!exercise.isEmpty()) {
                    System.out.println(exercise);
                }
            }

            return exercises;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
