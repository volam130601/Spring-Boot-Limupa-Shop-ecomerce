package com.example.limupashope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto implements Serializable {
    private Long id;
    private Integer quantity;
    private Long shoppingSessionId;
	private Long productId;
}

