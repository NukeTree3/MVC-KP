package com.nuketree3.example.mvckp.model.comment;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "product_id")
    private Long productID;
    @Column(name = "rating")
    private int rating;
    @Column(name = "comments_text")
    private String commentsText;

    public Comment(Long userID, Long productID, int rating, String commentsText) {
        this.userID = userID;
        this.productID = productID;
        this.rating = rating;
        this.commentsText = commentsText;
    }

    public Comment() {

    }
}
