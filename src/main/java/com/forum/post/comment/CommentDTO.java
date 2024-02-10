package com.forum.post.comment;

import lombok.Getter;

import java.util.UUID;

public class CommentDTO {

    @Getter
    private UUID postId;

    @Getter
    private Integer index;

    @Getter
    private String content;

    @Getter
    private Long ownerId;

}
