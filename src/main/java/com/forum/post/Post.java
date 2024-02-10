package com.forum.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forum.post.comment.Comment;
import com.forum.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "post")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Serializable {


    @Id
    @Column(name = "post_id", updatable = false, nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @Getter
    private UUID postId;


    @Column(name = "title", nullable = false, unique = true, length = 100)
    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

}
