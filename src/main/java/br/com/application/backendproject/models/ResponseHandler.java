package br.com.application.backendproject.models;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    
    public static ResponseEntity<Object> generateResponse(String message, org.springframework.http.HttpStatus status, Address responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", ((HttpStatusCode) status).value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,(HttpStatusCode) status);
    }


}
