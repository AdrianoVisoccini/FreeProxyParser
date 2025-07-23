package com.habrai.bot.service.habrParser;

import com.habrai.bot.pojo.HabrArticle;
import com.habrai.bot.pojo.gigachat.HabrCommentsPageArticle;

public interface IHabrParserService{
    HabrArticle parseArticle(String html);

    HabrCommentsPageArticle parseCommentsPage(String html);
}
