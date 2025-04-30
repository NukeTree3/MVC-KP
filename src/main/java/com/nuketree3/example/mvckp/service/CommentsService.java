package com.nuketree3.example.mvckp.service;

import com.nuketree3.example.mvckp.model.comment.Comment;
import com.nuketree3.example.mvckp.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentsService {
    CommentRepository commentRepository;

    public void addComments(Comment comment) {
        commentRepository.save(comment);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(Long commentId, String username) {
        commentRepository.deleteCommentByCommentIdAndUsername(commentId, username);
    }

    public double getAverageRanting(Long productID) {
        List<Comment> comments = getCommentsByProductID(productID);
        int count = comments.size();
        double sum = 0;
        for (Comment comment : comments) {
            sum += comment.getRating();
        }
        return sum / count;
    }

    public List<Comment> getCommentsByProductID(Long productID) {
        return commentRepository.getCommentsByProductID(productID);
    }
}
