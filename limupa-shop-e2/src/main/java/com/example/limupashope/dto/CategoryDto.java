package com.example.limupashope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {

	private Long categoryId;

    @NotEmpty
    @Length(min = 1)
    private String name;

    private String description;
    private boolean checkEdit;
//    private Long[] ids;


}
