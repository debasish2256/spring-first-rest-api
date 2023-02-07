package com.spring.project.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.project.dto.Restaurant;

import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
   private List<Restaurant> restaurants = new ArrayList<>();

   public Restaurant addRestaurant(Restaurant restaurant) {
      restaurants.add(restaurant);
	return restaurant;
   }

   public List<Restaurant> getAllRestaurants() {
      return restaurants;
   }

   public Restaurant getRestaurantByName(String name) {
      for (Restaurant restaurant : restaurants) {
         if (restaurant.getName().equals(name)) {
            return restaurant;
         }
      }
      return null;
   }

   public Restaurant updateRestaurant(Integer id, Restaurant updatedRestaurant) {
      for (Restaurant restaurant : restaurants) {
         if (restaurant.getName().equals(updatedRestaurant.getName())) {
            restaurant.setAddress(updatedRestaurant.getAddress());
            restaurant.setSpeciality(updatedRestaurant.getSpeciality());
            restaurant.setTotalStaffs(updatedRestaurant.getTotalStaffs());
            // update other variables if necessary
         }
      }
	return updatedRestaurant;
   }

   public void deleteRestaurant(Integer id) {
      for (Restaurant restaurant : restaurants) {
         if (restaurant.getName().equals(id)) {
            restaurants.remove(restaurant);
            break;
         }
      }
   }
}
