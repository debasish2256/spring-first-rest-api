package com.spring.project;

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
}
