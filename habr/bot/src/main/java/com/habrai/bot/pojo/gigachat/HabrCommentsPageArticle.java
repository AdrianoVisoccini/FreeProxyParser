package com.habrai.bot.pojo.gigachat;

import com.habrai.bot.pojo.HabrComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class HabrCommentsPageArticle {
    private String title;
    private String author;
    private String publicationDate;
    private List<HabrComment> comments;
}
