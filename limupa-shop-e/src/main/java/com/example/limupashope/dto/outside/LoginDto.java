package com.example.limupashope.dto.outside;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class LoginDto {
	@NotEmpty(message = "User Name cannot be empty!")
	private String username;

	@NotEmpty(message = "Password cannot be empty!")
	@Min(value = 6,  message="Password is greater than 6")
	private String password;
	
	private Boolean rememberMe;
}
