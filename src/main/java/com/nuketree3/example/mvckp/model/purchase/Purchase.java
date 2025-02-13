package com.nuketree3.example.mvckp.model.purchase;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table(name = "purchase")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "basket_id")
    private Long basketId;
    @Column(name = "purchaser_id")
    private Long purchaser;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "count")
    private int count;
}
