package com.example.limupashope.dto.outside;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class RegisterDto {
	@NotEmpty(message = "User Name cannot be empty!")
	private String username;

	@NotEmpty(message = "Password cannot be empty!")
	@Min(value = 6,  message="Password is greater than 6")
	private String password;
	
	@NotEmpty(message = "Re-Password cannot be empty!")
	@Min(value = 6, message="Re-Password is greater than 6" ) 
	private String rePassword;
	
	@NotEmpty(message = "First Name cannot be empty!")
	private String firstName;
	@NotEmpty(message = "Last Name cannot be empty!")
	private String lastName;
}
