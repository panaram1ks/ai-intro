package com.parom.aiintro;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class OllamaAIServiceImpl {

    private final OllamaChatModel chatModel;

    public OllamaAIServiceImpl(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateResponse(String userPrompt) {
        Prompt prompt = new Prompt(userPrompt);
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
}
