package com.example.limupashope.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private Long id;
    private String name;
    private Double total;

    private Long userId;
    private Long paymentId;
}
