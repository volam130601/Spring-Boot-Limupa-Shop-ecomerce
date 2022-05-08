package com.example.limupashope.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	private Long roleId;
	@NotEmpty
	private String name;
	@NotEmpty
	private String code;
	
	private Boolean checkEdit;
}
