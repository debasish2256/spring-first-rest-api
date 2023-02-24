package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import com.spring.project.dto.Order;
import com.spring.project.dto.Product;
import com.spring.project.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	@Autowired
    private OrderRepository orderRepository;
    
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    
    public Optional<Order> getOrderById(Integer id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
