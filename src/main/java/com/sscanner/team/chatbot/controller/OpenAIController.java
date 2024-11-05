package com.sscanner.team.chatbot.controller;

import com.sscanner.team.chatbot.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trash")
@RequiredArgsConstructor()
public class OpenAIController {

    private final OpenAIService openAIService;

    @GetMapping("/classify/{item}")
    public ResponseEntity<String> classifyItem(@PathVariable String item) {
        String classification = openAIService.classifyTrash(item);
        return ResponseEntity.ok(classification);
    }
}
