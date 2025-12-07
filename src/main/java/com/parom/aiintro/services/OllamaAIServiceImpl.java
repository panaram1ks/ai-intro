package com.parom.aiintro.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parom.aiintro.model.Answer;
import com.parom.aiintro.model.GetCapitalRequest;
import com.parom.aiintro.model.GetCapitalResponse;
import com.parom.aiintro.model.Question;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class OllamaAIServiceImpl {

    private final OllamaChatModel chatModel;
    private final ObjectMapper objectMapper;

    public OllamaAIServiceImpl(OllamaChatModel chatModel, ObjectMapper objectMapper) {
        this.chatModel = chatModel;
        this.objectMapper = objectMapper;
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

    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        System.out.println("format: \n" + format);
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(
                Map.of(
                        "stateOrCountry", getCapitalRequest.stateOrCountry(),
                        "format", format
                ));
        ChatResponse response = chatModel.call(prompt);
        System.out.println(response.getResult().getOutput().getText());
          return converter.convert(Objects.requireNonNull(response.getResult().getOutput().getText()));
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
