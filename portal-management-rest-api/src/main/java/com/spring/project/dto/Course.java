package com.spring.project.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
    @Id
    private String course_id;
    private String title;
    private String description;
    private String duration;

    @ManyToMany(fetch = FetchType.LAZY)
    List<Student> studentList = new ArrayList<>();

}