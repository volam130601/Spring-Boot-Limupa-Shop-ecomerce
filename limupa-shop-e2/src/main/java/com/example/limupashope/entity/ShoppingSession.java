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
public class ShoppingSession implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;

    @OneToMany(mappedBy = "shoppingSession")
    private List<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    private User user;
}
