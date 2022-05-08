package com.example.limupashope.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long productId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private Integer quantity;

//    @Column(nullable = false)
    private String SKU;
    private Integer rating;
    private Boolean wish = false;
    private short status;
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;

    @OneToOne(mappedBy = "product_cart")
    private CartItem cartItem;

    //order_product
    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orders;
}
