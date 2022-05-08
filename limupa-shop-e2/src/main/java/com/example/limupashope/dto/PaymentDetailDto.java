package com.example.limupashope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailDto implements Serializable {
    private Long id;
    private Integer amount;
    private String provider;
    private String status;
    private Long orderId;
}
