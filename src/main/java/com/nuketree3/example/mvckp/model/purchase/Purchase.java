package com.nuketree3.example.mvckp.model.purchase;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "purchase")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "purchaser_id")
    private Long purchaser;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "date")
    private Date date;
    @Column(name = "count")
    private int count;
}
