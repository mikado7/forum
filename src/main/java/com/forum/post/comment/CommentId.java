package com.forum.post.comment;

import com.forum.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class CommentId  implements Serializable {

    @Column(name = "post_id", nullable = false, updatable = false, insertable = false)
    private UUID postId;
    @Column(name = "index", nullable = false)
    private Integer index;
}
