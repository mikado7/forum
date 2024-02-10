package com.forum.post;

import lombok.Getter;

public class PostDTO {

    @Getter
    private String title;
    @Getter
    private String description;

    @Getter
    private Long ownerId;


}
