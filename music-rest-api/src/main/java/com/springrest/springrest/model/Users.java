package com.springrest.springrest.model;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "userName is mandatory")
    private String userName;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "please provide valid email")
    private String email;

    @NotBlank(message = "phoneNumber is mandatory")
    @Pattern(regexp="(0/91)?[7-9][0-9]{9}",  message = "please provide valid phone number")
    private String phoneNumber;

    private String gender;
    private String dateOfBirth;

    private Timestamp createdDate;
    private Timestamp updatedDate;
    @JsonIgnore
    private ArrayList<Integer>playList;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Status status;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Role role;

}
