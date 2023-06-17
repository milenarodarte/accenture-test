package com.milena.companyAndSuppliers.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


@Service
public class CEPValidator {
    @Autowired
    private RestTemplate restTemplate ;

    @Autowired
    public CEPValidator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean validateCEP(String cep) {
        String url = "http://cep.la/api/{cep}";
        ParameterizedTypeReference<Map<String, Object>> responseType = new ParameterizedTypeReference<Map<String, Object>>() {};
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, cep);
        ResponseEntity<Map<String, Object>> responseJson = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = responseJson.getBody();}
        else {
            return false;
        }

        String productsJson = response.getBody();

        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isParana(String cep) throws IOException {
        URL url = new URL("http://cep.la/"+cep);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();

        ObjectMapper mapper = new ObjectMapper();

        return true;
    }

}
