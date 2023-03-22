package br.com.application.backendproject.services.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

public class AddressUtilities {

    public static boolean cepValidator(String cep) throws ParseException, JsonMappingException, JsonProcessingException{
        
        try{

            RestTemplate restTemplate = new RestTemplate(); 
            String url = "http://viacep.com.br/ws/"+cep+"/json/";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); 
            String jsonBody = response.getBody();
            ObjectMapper mapper = new JsonMapper();
            JsonNode json = mapper.readTree(jsonBody);
    
            String returnedCep = json.get("cep").asText();
            if(returnedCep != null){
                return true;
            } 
            return false;

        } catch(Exception e){
            return false;
        }
        
       
    }
    
    
}
