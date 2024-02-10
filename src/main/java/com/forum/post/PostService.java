package com.forum.post;

import com.forum.post.comment.CommentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
;import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Post updatedPost) {
        return postRepository.save(updatedPost);
    }

    public String deletePost(UUID postId) {
        return "Post deleted successfully";
    }

    public Post getPostByPostId(UUID postId) {
        return postRepository.getPostByPostId(postId);
    }
}

