package com.parom.aiintro;

import com.parom.aiintro.model.Answer;
import com.parom.aiintro.model.Question;
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

    public Answer getAnswer(Question question) {
        System.out.println("I was called");
        Prompt prompt = new Prompt(question.question());
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }
}
