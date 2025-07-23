package com.habrai.bot.controller;

import com.habrai.bot.pojo.HabrCommentResponse;
import com.habrai.bot.pojo.HabrCommentTreeResponse;
import com.habrai.bot.pojo.gigachat.FileResponse;
import com.habrai.bot.pojo.HabrArticle;
import com.habrai.bot.pojo.gigachat.HabrCommentsPageArticle;
import com.habrai.bot.service.IHabrApiService;
import com.habrai.bot.service.articleWriter.IArticleWriter;
import com.habrai.bot.service.bot.IBotService;
import com.habrai.bot.service.gpt.GigachatService;
import com.habrai.bot.service.habrParser.HabrParserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
public class HabrAIController {

    private final GigachatService gigachatService;
    private final IHabrApiService habrApiService;
    private final IBotService botService;
    private final HabrParserServiceImpl habrParserServiceImpl;
    private final IArticleWriter articleWriter;

    @PostMapping("/testGigachat")
    public String testGigachat(@RequestBody String request) {
//        return gigachatService.getResponse(request);
        return botService.processArticleAndMakeAComment(request);
    }

    @PostMapping("/file")
    public FileResponse fileTest(@RequestBody MultipartFile request) {
        return gigachatService.uploadFile(request);
    }

    @PostMapping("/deleteFile")
    public String fileTest(@RequestParam String request) {
        return gigachatService.deleteFile(request);
    }
    
    @GetMapping("/article")
    public HabrArticle article(@RequestParam String request) {
        return habrApiService.getArticle(request);
    }

    @GetMapping("/comment")
    public HabrCommentsPageArticle comments(@RequestParam String request) {
        return habrApiService.getCommentsPage(request);
    }

    @GetMapping("/comments")
    public HabrCommentTreeResponse commentTree(@RequestParam String request, @RequestParam String commentId) {
        HabrCommentsPageArticle comments = habrApiService.getCommentsPage(request);
        return habrApiService.getCommentTree(commentId, comments);
    }

    @GetMapping("/reply")
    public String reply(@RequestParam String request, @RequestParam String commentId) {
        FileResponse file = gigachatService.uploadFile(articleWriter.writeArticleFile(habrApiService.getArticle(request)));

        HabrCommentTreeResponse commentTree = habrApiService.getCommentTree(commentId, habrApiService.getCommentsPage(request+"/comments"));
        String relyPrompt = "вот список ккоментариев к стать:"+ commentTree.getCommentTree() + " Твои комментарии имеют ник AdrianoVisoccinш, отвечай от его имени" + "продолжи диалог, ответив на последний комментарий. не используй никаких вводных слов, пиши так как будто это твои мысли. Постарайся использовать личный опыт в комментарии" +
                " Пиши легко и иронично подшучивая над статьей, не старайся быть излишне учтивым и обходительным. Будь немного резким";
        String newReply =  gigachatService.getResponse(relyPrompt, file.getId());
        return newReply;
    }

    @PostMapping("/testReply")
    public HabrCommentResponse testReply(@RequestParam String articleId, @RequestParam String parentCommentId, @RequestParam String commentText) {
        return habrApiService.postReply(articleId, parentCommentId, commentText);
    }

    @PostMapping("/initiate")
    public String testReply(@RequestParam String articleUrl) {
        return botService.processArticleAndMakeAComment(articleUrl);
    }
}
