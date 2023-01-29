package com.spring.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

	private final String API_KEY = "4a4c38ba105d44358d3c8ac66bc23be3";

	@GetMapping("/weather")
	public String getWeather() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.weatherbit.io/v2.0/current?city=Raleigh&country=US&key=" + API_KEY;
		String weatherData = restTemplate.getForObject(url, String.class);
		return weatherData;
	}
}
