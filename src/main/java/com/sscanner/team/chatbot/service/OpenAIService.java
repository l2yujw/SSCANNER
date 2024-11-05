package com.sscanner.team.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String classifyTrash(String item) {
        String endpoint = "https://api.openai.com/v1/chat/completions";

        String requestBody = String.format(
                "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"user\",\"content\":\"" +
                        "%s가 분리수거인지 알려주고" +
                        "어떻게 분리수거해야 하는지 알려줘" +
                        "이외에 정보나 말은 하지마\"}]}",
                item
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 재시도 횟수 설정
        int retryCount = 3;

        for (int i = 0; i < retryCount; i++) {
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(endpoint, entity, String.class);
                return extractClassification(response.getBody());
            } catch (HttpClientErrorException e) {
                // API 호출 중 발생한 HTTP 오류 처리
                if (e.getStatusCode().value() == 429) {
                    if (i < retryCount - 1) {
                        // 요청 수 초과 시 대기 후 재시도
                        try {
                            TimeUnit.SECONDS.sleep(2); // 2초 대기 후 재시도
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            return "요청이 중단되었습니다.";
                        }
                    } else {
                        return "요청 수가 초과되었습니다. 잠시 후 다시 시도해 주세요.";
                    }
                } else {
                    return "오류가 발생했습니다: " + e.getMessage();
                }
            } catch (Exception e) {
                // 다른 예외 처리
                return "오류가 발생했습니다: " + e.getMessage();
            }
        }

        return "최대 재시도 횟수를 초과했습니다. 나중에 다시 시도해 주세요.";
    }

    private String extractClassification(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String classification = jsonNode.get("choices").get(0).get("message").get("content").asText();
            return classification.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "분류 과정에서 오류가 발생했습니다.";
        }
    }
}
