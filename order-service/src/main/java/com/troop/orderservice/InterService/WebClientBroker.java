package com.troop.orderservice.InterService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.troop.orderservice.cfg.OrderURI.WEBCLIENT_BASE_URL;

@Component
@AllArgsConstructor
public class WebClientBroker {

    public Map postRequest(String URL, String json, String ... bearertoken){
        System.out.println(bearertoken);
    MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Content-Type", "application/json");
        header.add("Accept", "application/json");
        if(bearertoken.equals("[]")) {
        header.add("Authorization",bearertoken[0]);
        }

    WebClient webClient = WebClient.create(WEBCLIENT_BASE_URL);
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
