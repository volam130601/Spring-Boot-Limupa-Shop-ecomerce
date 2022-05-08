package com.example.limupashope.dto.outside;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
	private Map<String , Object> properties;
	private String from;
	private String to;
	private String subject;
	private String template;
}
