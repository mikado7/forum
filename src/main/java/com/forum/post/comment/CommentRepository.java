package com.forum.post.comment;

import com.forum.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentsByPost(Post post);

    Optional<Comment> findCommentByCommentId(CommentId commentId);
    void deleteCommentByCommentId(CommentId commentId);


}
