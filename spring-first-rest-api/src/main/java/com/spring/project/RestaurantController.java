package com.spring.project;
import java.util.List;

import com.spring.project.dto.Restaurant;
import com.spring.project.repository.RestaurantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
	@Autowired
    private RestaurantRepository repository;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
    	return repository.findAll();
    }

    @GetMapping("/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name) {
    	return repository.findByName(name);
    }

    @PostMapping("/addRestaurant")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
    	return repository.save(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable int id, @RequestBody Restaurant restaurant) {
    	restaurant.setId(id);
        return repository.save(restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable int id) {
    	repository.deleteById(id);
    }
}
