package com.tinyego.chatgpt;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatgptService {
	private String apiKey =""; // sk-proj-보안키 입력

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl = "https://api.openai.com/v1/chat/completions"; // API 주소

    public String getResponse(String prompt) {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Authorization", "Bearer " + apiKey);
    	headers.set("Content-Type", "application/json");
    	System.out.println("===> prompt(999) :" + prompt);
    	
    	// 2 개의 매개변수를 받아 결과를 리턴함
    	String keyvalue = "You are ChatGPT, a large language model trained by OpenAI";
        
    	// (1) 시스템을 세뇌시키는 content
    	// (2) 사용자가 요청하는 content
    	String body = String.format("{"
    			+ "\"model\": \"gpt-3.5-turbo\","
    			+ " \"messages\": "
    			+ "[{\"role\": \"system\", \"content\": \"%s\"},"
    			+ "{\"role\": \"user\", \"content\": \"%s\"}]}",
    			keyvalue, prompt);
        
         HttpEntity<String> entity = new HttpEntity<>(body, headers);      
         ResponseEntity<String> response 
         		= restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
	return response.getBody();
    }
}
