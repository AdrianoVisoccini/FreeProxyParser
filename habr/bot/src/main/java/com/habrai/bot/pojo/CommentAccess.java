package com.habrai.bot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentAccess {

    @JsonProperty("isCanComment")
    private boolean canComment;

    @JsonProperty("cantCommentReasonKey")
    private String cantCommentReasonKey;

    @JsonProperty("cantCommentReason")
    private String cantCommentReason;

    @JsonProperty("data")
    private Object data;  // Тип Object, так как в примере null

}
