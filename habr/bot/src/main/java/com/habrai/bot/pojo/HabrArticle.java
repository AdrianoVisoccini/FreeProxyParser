package com.habrai.bot.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class HabrArticle {
    private String itemId;
    private String title;
    private String author;
    private String publicationDate;
    private int viewsCount;
    private int commentsCount;
    private List<String> tags = new ArrayList<>();
    private List<String> hubs = new ArrayList<>();
    private String content;
}
