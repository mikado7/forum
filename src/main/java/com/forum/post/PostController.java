package com.forum.post;

import com.forum.post.comment.Comment;
import com.forum.post.comment.CommentDTO;
import com.forum.post.comment.CommentId;
import com.forum.post.comment.CommentService;
import com.forum.security.CustomUserDetailsService;
import com.forum.security.User;
import com.forum.security.UserPrincipal;
import com.forum.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.forum.post.comment.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/posts")
public class PostController
{
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Post> addPost(@RequestBody PostDTO post) {
        User user = customUserDetailsService.getUserById(post.getOwnerId());
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setDescription(post.getDescription());
        newPost.setOwner(user);
        try {
            return new ResponseEntity<>(postService.addPost(newPost), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/categories")
    public ResponseEntity<List<Post>> getAllPosts(@RequestBody PostDTO post) {
        try {
            return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("@postRepository.findById(#postId).orElse(null)?.owner?.username == authentication.principal.username or hasAuthority('ADMIN')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable UUID postId) {
        logger.info(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        try {
            return new ResponseEntity<>(postService.deletePost(postId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("@postRepository.findById(#postId).orElse(null)?.owner?.username == authentication.principal.username or hasAuthority('ADMIN')")
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody PostDTO updatedPost, @PathVariable UUID postId) {
        try {
            Post post = postService.getPostByPostId(postId);
            post.setTitle(updatedPost.getTitle());
            post.setDescription(updatedPost.getDescription());
            return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostByPostId(@PathVariable UUID postId) {
        try {
            return new ResponseEntity<>(postService.getPostByPostId(postId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable UUID postId) {
        try {
            return new ResponseEntity<>(commentService.getCommentsByPost(postService.getPostByPostId(postId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(@RequestBody CommentDTO comment) {
        logger.info("dupa" + SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
        Comment newComment = new Comment();
        newComment.setOwner(customUserDetailsService.getUserById(comment.getOwnerId()));
        newComment.setContent(comment.getContent());
//        newComment.setPost(postService.getPostByPostId(comment.getPostId()));
        newComment.setCommentId(new CommentId(comment.getPostId(), comment.getIndex()));
        return new ResponseEntity<>(commentService.addComment(newComment), HttpStatus.CREATED);
    }

    @PreAuthorize("@commentService.getCommentByPostAndIndex(#postId, #index)?.owner?.username == authentication.principal.username or hasAuthority('ADMIN')")
    @PutMapping("/{postId}/comments/{index}")
    public ResponseEntity<Comment> updateComment(@RequestBody CommentDTO updatedComment, @PathVariable UUID postId, @PathVariable int index) {
        try {
            Comment comment = commentService.getCommentByPostAndIndex(updatedComment.getPostId(), updatedComment.getIndex());
            comment.setContent(updatedComment.getContent());
            return new ResponseEntity<>(commentService.updateComment(comment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("@commentService.getCommentByPostAndIndex(#postId, #index)?.owner?.username == authentication.principal.username or hasAuthority('ADMIN')")
    @DeleteMapping("/{postId}/comments/{index}")
    public ResponseEntity<String> deleteComment(@PathVariable UUID postId, @PathVariable int index) {
            return new ResponseEntity<>(commentService.deleteComment(postId, index), HttpStatus.OK);
    }

}
