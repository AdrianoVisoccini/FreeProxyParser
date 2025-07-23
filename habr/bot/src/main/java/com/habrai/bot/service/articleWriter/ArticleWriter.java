package com.habrai.bot.service.articleWriter;

import com.habrai.bot.pojo.ArticleFile;
import com.habrai.bot.pojo.HabrArticle;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArticleWriter implements IArticleWriter {

    @Override
    public MultipartFile writeArticleFile(HabrArticle article) {
        ArticleFile articleFile = new ArticleFile(article.getTitle(), article.getTitle() + ".txt", "text/plain", article.getContent().getBytes());
        return articleFile;
    }
}
