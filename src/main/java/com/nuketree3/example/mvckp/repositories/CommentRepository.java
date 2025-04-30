package com.nuketree3.example.mvckp.repositories;

import com.nuketree3.example.mvckp.model.comment.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByProductID(Long productID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM comments WHERE id = :id AND user_id = (SELECT id FROM purchaser WHERE login = :username)", nativeQuery = true)
    void deleteCommentByCommentIdAndUsername(@Param("id") Long commentId, @Param("username") String username);
}
