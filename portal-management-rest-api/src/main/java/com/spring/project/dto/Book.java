package com.spring.project.dto;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Book {
    @Id
    private String book_id;
    private String title;
    private String author;
    private String description;
    private String price;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

}