package com.springrest.springrest.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
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
	@Pattern(regexp = "(0/91)?[7-9][0-9]{9}", message = "please provide valid phone number")
	private String phoneNumber;
	private String gender;
	private String dateOfBirth;
	private Integer roleId;
}
