package com.troop.orderservice.InterService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class WebClientBroker {

//    private final WebClient webClient;

    public Map postRequest(String URL, String json){

    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Content-Type", "application/json");
        header.add("Accept", "application/json");

    WebClient webClient = WebClient.create("http://localhost:8083");
    return  webClient
            .post()
            .uri(URL)
            .headers(headers -> headers.addAll(header))
            .bodyValue(json)
            .retrieve()
            .bodyToMono(Map.class)
            .block();
    }

}
