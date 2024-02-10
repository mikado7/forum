package com.forum.post.comment;

import com.forum.post.Post;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.getCommentsByPost(post);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment updatedComment) {
        return commentRepository.save(updatedComment);
    }

    @Transactional
    public String deleteComment(UUID postId, int index) {
        commentRepository.deleteCommentByCommentId(new CommentId(postId, index));
        return "Comment deleted successfully";
    }

    public Comment getCommentByPostAndIndex(UUID postId, int index) {
        return commentRepository.findCommentByCommentId(new CommentId(postId, index)).orElse(null);
    }
}
