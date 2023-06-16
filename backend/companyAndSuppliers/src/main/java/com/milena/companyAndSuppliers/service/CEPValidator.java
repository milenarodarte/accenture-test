package com.milena.companyAndSuppliers.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class CEPValidator {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public CEPValidator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public boolean validateCEP(String cep) {
        String url = "http://cep.la/{cep}";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, cep);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isParana(String cep) throws IOException {
        String url = "http://cep.la/" + cep;

        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        String jsonResponse = response.getBody();

        // Analisar a resposta JSON
        // Dependendo da estrutura da resposta JSON, você pode usar o Jackson ou Gson para analisá-la e obter a UF
        // Vou usar o Jackson neste exemplo

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseData = mapper.readTree(jsonResponse);
            System.out.println("dataaa: " + responseData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;

    }
}
