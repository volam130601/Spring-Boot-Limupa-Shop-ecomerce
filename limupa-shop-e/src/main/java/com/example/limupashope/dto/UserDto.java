package com.example.limupashope.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
public class UserDto  {
	private Long id;
	
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	
    private String email;
    private String address;
    private Integer phone;
    
    private Long roleId;
    private boolean checkEdit;
   
}
