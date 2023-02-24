package com.spring.project.service;

import java.util.List;
import java.util.Optional;

import com.spring.project.dto.Address;
import com.spring.project.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

	@Autowired
    private AddressRepository addressRepository;
    
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    
    public Optional<Address> getAddressById(Integer id) {
        return addressRepository.findById(id);
    }
    
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }
    
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }
    
    public void deleteAddressById(Integer id) {
        addressRepository.deleteById(id);
    }
}