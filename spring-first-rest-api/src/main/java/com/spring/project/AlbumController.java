package com.spring.project;

import java.util.List;

import com.spring.project.dto.Album;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AlbumController {
	private final RestTemplate restTemplate;

    public AlbumController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	@GetMapping("/albums")
    public List<Album> getAlbums() {
        ResponseEntity<List<Album>> response = restTemplate.exchange("https://jsonplaceholder.typicode.com/albums",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Album>>() {
                });
        return response.getBody();
    }
}
