package com.habrai.bot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequest {
    private String articleId;
    private TextContent text;
    private String parentId;

    @JsonProperty("isPost")
    private boolean isPost;

    private String idempotenceKey;

    // Геттеры и сеттеры
}