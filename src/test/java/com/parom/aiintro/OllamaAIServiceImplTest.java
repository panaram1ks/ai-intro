package com.parom.aiintro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OllamaAIServiceImplTest {

    @Autowired
    OllamaAIServiceImpl ollamaAIService;

    @Test
    void generateResponse() {
        String answer = ollamaAIService.generateResponse("What is a meaning of life?");
        System.out.println(answer);
    }
}