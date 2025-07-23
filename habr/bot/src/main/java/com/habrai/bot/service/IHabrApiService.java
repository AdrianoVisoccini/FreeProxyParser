package com.habrai.bot.service;

import com.habrai.bot.pojo.HabrArticle;
import com.habrai.bot.pojo.HabrCommentResponse;
import com.habrai.bot.pojo.HabrCommentTreeResponse;
import com.habrai.bot.pojo.gigachat.HabrCommentsPageArticle;

public interface IHabrApiService {
    HabrArticle getArticle(String requestUrl);

    HabrCommentResponse postReply(String articleId, String parentCommentId, String commentText);

    HabrCommentsPageArticle getCommentsPage(String requestUrl);

    HabrCommentTreeResponse getCommentTree(String parentCommentId, HabrCommentsPageArticle comments);
}
