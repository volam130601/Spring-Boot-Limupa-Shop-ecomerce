package com.example.limupashope.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private Long productId;
    private String name;
    private String description;

    private String SKU;
    private Double unitPrice;
    private String image;
    private MultipartFile imageFile;
    
    private Integer quantity;

    private Integer rating;
    private Boolean wish = false;
    private short status;
    private Double discount;

    private Long categoryId;

    private boolean checkEdit;

}
