package com.parom.aiintro.services;

import com.parom.aiintro.model.Answer;
import com.parom.aiintro.model.GetCapitalRequest;
import com.parom.aiintro.model.Question;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OllamaAIServiceImpl {

    private final OllamaChatModel chatModel;

    public OllamaAIServiceImpl(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getCapitalWithInfo;

    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }

    public Answer getCapital(GetCapitalRequest getCapitalRequest) {
//        PromptTemplate promptTemplate = new PromptTemplate("what is the capital of " +getCapitalRequest.stateOrCountry() + "?" );
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
//        Prompt prompt = promptTemplate.create();
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));
        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getText());
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
