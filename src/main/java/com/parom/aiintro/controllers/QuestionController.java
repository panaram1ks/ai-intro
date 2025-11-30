package com.parom.aiintro.controllers;

import com.parom.aiintro.OllamaAIServiceImpl;
import com.parom.aiintro.model.Answer;
import com.parom.aiintro.model.Question;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OllamaAIServiceImpl ollamaAIService;

    public QuestionController(OllamaAIServiceImpl ollamaAIService) {
        this.ollamaAIService = ollamaAIService;
    }


    @PostMapping
    public Answer askQuestion(Question question){
        return ollamaAIService.getAnswer(question);
    }
}
