package com.forum.post.comment;

import com.forum.post.Post;
import com.forum.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "comment")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {


    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false, insertable = false, updatable = false)
    private Post post;

    @EmbeddedId
    private CommentId commentId;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

}
