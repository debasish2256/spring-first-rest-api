package com.spring.project.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Integer id;
    private String name;
    private String landmark;
    @NotBlank
    @Size(min = 10, max = 10)
    private String phoneNumber;
    @NotBlank
    @Size(min = 10, max = 10)
    private String zipcode;
    private String state;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
