package com.example.limupashope.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment_detail")
public class PaymentDetail implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private String provider;
    @Column(nullable = false)
    private short status;

    @OneToOne(mappedBy = "paymentDetail")
    private Order order;
}
