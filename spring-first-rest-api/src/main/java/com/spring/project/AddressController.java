package com.spring.project;

import java.util.List;
import java.util.Optional;

import com.spring.project.dto.Address;
import com.spring.project.dto.User;
import com.spring.project.repository.UserRepository;
import com.spring.project.service.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-management/addresses")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
    	return addressService.createAddress(address);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id) {
        Optional<Address> address = addressService.getAddressById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok(address.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }
    
    @PutMapping("/{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Integer id) {
        address.setId(id);
        return addressService.updateAddress(address);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAddressById(@PathVariable Integer id) {
        addressService.deleteAddressById(id);
    }
}