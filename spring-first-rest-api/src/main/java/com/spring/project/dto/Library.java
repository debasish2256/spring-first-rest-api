package com.spring.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String libraryName;
    private String libraryAddress;
    private String libraryNumber;
    private String libraryFacilities;

    public Library() {
    }

    public Library(String libraryName, String libraryAddress, String libraryNumber, String libraryFacilities) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
        this.libraryNumber = libraryNumber;
        this.libraryFacilities = libraryFacilities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public void setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getLibraryFacilities() {
        return libraryFacilities;
    }

    public void setLibraryFacilities(String libraryFacilities) {
        this.libraryFacilities = libraryFacilities;
    }
}
