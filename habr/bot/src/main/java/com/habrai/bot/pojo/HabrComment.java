package com.habrai.bot.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class HabrComment {

    private String id;
    private String parentId;
    private String level;
    private String author;
    private String text;
    private String datePublished;
    private int score;
    private List<HabrComment> replies;
}
