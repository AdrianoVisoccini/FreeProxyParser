package com.habrai.bot.service.articleWriter;

import com.habrai.bot.pojo.HabrArticle;
import org.springframework.web.multipart.MultipartFile;

public interface IArticleWriter {
    MultipartFile writeArticleFile(HabrArticle article);
}
