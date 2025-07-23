package com.habrai.bot.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class HabrCommentTreeResponse {

    String commentTree;

    String lastCommentId;

    int amount;
}
