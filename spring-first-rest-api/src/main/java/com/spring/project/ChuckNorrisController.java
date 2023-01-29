package com.spring.project;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ChuckNorrisController {

	@GetMapping("/joke")
	public String getJoke(@RequestParam String category) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.chucknorris.io/jokes/random?category="+category;
		String joke = restTemplate.getForObject(url, String.class);
		return joke;
	}
	
	
	@GetMapping("/joke-json")
	public ResponseEntity<String> getJokeWithJsonResponse(@RequestParam String category) throws IOException, InterruptedException, JSONException {
		var uri = URI.create("https://api.chucknorris.io/jokes/random?category="+category);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest
		        	  .newBuilder()
		        	  .uri(uri)
		        	  .header("accept", "application/json")
		        	  .GET()
		        	  .build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return ResponseEntity.status(response.statusCode()).body(response.body());
	}
}
