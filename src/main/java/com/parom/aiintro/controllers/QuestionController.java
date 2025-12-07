package com.parom.aiintro.controllers;

import com.parom.aiintro.model.GetCapitalRequest;
import com.parom.aiintro.services.OllamaAIServiceImpl;
import com.parom.aiintro.model.Answer;
import com.parom.aiintro.model.Question;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OllamaAIServiceImpl ollamaAIService;

    public QuestionController(OllamaAIServiceImpl ollamaAIService) {
        this.ollamaAIService = ollamaAIService;
    }


    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return ollamaAIService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer askCapital(@RequestBody GetCapitalRequest getCapitalRequest) {
        return ollamaAIService.getCapital(getCapitalRequest);
    }
}
