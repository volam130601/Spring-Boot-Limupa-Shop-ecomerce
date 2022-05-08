package com.example.limupashope.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderProductId implements Serializable {
    @Column(name = "product_id")
    Long productId;
    @Column(name = "order_id")
    Long orderId;
}
