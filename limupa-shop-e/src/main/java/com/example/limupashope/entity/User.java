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
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    
    private String email;
    private String address;
    private Integer phone;

    @OneToOne(mappedBy = "user")
    private Order order;

    @OneToOne(mappedBy = "user")
    private ShoppingSession session;
    
    @ManyToOne
    @JoinColumn(name = "role_id" , nullable = false)
    private Role role;
}
