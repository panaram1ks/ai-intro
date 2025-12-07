package com.parom.aiintro.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parom.aiintro.model.GetCapitalRequest;
import com.parom.aiintro.model.GetCapitalResponse;
import com.parom.aiintro.services.OllamaAIServiceImpl;
import com.parom.aiintro.model.Answer;
import com.parom.aiintro.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OllamaAIServiceImpl ollamaAIService;

    public QuestionController(OllamaAIServiceImpl ollamaAIService) {
        this.ollamaAIService = ollamaAIService;
    }

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return ollamaAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public GetCapitalResponse askCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public Answer askCapitalWithInfo(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaAIService.getCapitalWithInfo(getCapitalRequest);
    }
}
