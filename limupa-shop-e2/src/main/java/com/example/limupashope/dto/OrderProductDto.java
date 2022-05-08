package com.example.limupashope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto implements Serializable {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
}
