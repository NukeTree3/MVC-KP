package com.nuketree3.example.mvckp.repositories;

import com.nuketree3.example.mvckp.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByProductID(Long productID);
}
