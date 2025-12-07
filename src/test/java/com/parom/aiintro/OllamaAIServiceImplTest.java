package com.parom.aiintro;

import com.parom.aiintro.services.OllamaAIServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OllamaAIServiceImplTest {

    @Autowired
    OllamaAIServiceImpl ollamaAIService;

    @Test
    void generateResponse() {
        String answer = ollamaAIService.generateResponse("Write a python script to output numbers from 1 to 100");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}