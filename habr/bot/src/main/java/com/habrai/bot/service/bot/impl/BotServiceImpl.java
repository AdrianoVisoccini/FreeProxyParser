package com.habrai.bot.service.bot.impl;

import com.habrai.bot.pojo.HabrCommentResponse;
import com.habrai.bot.pojo.HabrCommentTreeResponse;
import com.habrai.bot.pojo.gigachat.FileResponse;
import com.habrai.bot.pojo.HabrArticle;
import com.habrai.bot.pojo.gigachat.HabrCommentInfo;
import com.habrai.bot.service.IHabrApiService;
import com.habrai.bot.service.articleWriter.IArticleWriter;
import com.habrai.bot.service.bot.IBotService;
import com.habrai.bot.service.gpt.GigachatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotServiceImpl implements IBotService {

    private final IHabrApiService habrApiService;
    private final GigachatService gigachatService;
    private final IArticleWriter articleWriter;

    @Override
    public String processArticleAndMakeAComment(String articleUrl) {
        ArrayList<String> sentReplies = new ArrayList<>();
        HabrArticle article = habrApiService.getArticle(articleUrl);
        int totalComments = 0;

        FileResponse file = gigachatService.uploadFile(articleWriter.writeArticleFile(article));
//        String initialCommentPrompt = "прочитай стаью из вложения " + file.getId() + "и придумай ккоммментарий к статье, который не звучал бы как будто ты нейросеть. не используй никаких вводных слов, не начинай с фразы давай разберемся, переходи сразу к делу, пиши так как будто это твои мысли. Постарайся использовать личный опыт в комментарии. " +
//                "Пиши легко и иронично подшучивая над статьей, не старайся быть излишне учтивым и обходительным. Будь немного резким. Отвечай коротко, не больше 50 слов";

 String initialCommentPrompt = "прочитай стаью из вложения " + file.getId() + "и придумай ккоммментарий к статье, который не звучал бы как будто ты нейросеть. не используй никаких вводных слов, не начинай с фразы давай разберемся, переходи сразу к делу, пиши так как будто это твои мысли. не начинай со слова статья, пиши сразу свое мнение о тезисах. Постарайся использовать личный опыт в комментарии. " +
                "Пиши легко и иронично, не старайся быть излишне учтивым и обходительным. Подметь хорошие аргументы в статье и согласись сними. Отвечай коротко, не больше 50 слов. не используй эмоджи";

        String reply = gigachatService.getResponse(initialCommentPrompt, file.getId());
        sentReplies.add(reply);
        HabrCommentResponse commentResponse = habrApiService.postReply(article.getItemId(), null, reply);
        String parentCommentId = commentResponse.getData().getId();
        totalComments++;
        String result = null;
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000 * 60 * 15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log.info("Обработка статьи " + articleUrl + " " + i + " раз");
            log.info("Parent comments id: " + commentResponse.getData().getId());
            log.info(articleUrl+"/comments");
            HabrCommentTreeResponse commentTree = habrApiService.getCommentTree(parentCommentId, habrApiService.getCommentsPage(articleUrl + "/comments"));
//            String relyPrompt = "вот список ккоментариев к статье:" + commentTree.getCommentTree() + " Твои комментарии имеют ник" + "definitelyRealPerson" + "продолжи диалог, ответив на последний комментарий. не используй никаких вводных слов, не наяинай фразы с нучто ж давай разберемся, никогда не начинай фазу со слов давай разберемся и ну что ж, давай посмотрим итд. пиши так как будто это твои мысли. Постарайся использовать личный опыт в комментарии, отвечай коротко, не больше 30 слов" +
//                    " Пиши легко и иронично подшучивая над ответчиком, не будь излишне учтивым и обходительным. Будь резким, отвечай грубо и ндаменно. Если с тобой соглашаются - коротко согласись в ответ. Воспринимай все что тебе пишут с иронией и сарказмом. Не обращайся к другим пользователям по имени. Не повторяй фразы и не дублируй одну мысль дважды";

             String relyPrompt = "вот список ккоментариев к статье:" + commentTree.getCommentTree() + " Твои комментарии имеют ник" + "reallyPoliteHuman" + "продолжи диалог, ответив на последний комментарий. не используй никаких вводных слов, не начинай фразы с нучто ж давай разберемся, никогда не начинай фазу со слов давай разберемся и ну что ж, давай посмотрим итд. пиши так как будто это твои мысли. Постарайся использовать личный опыт в комментарии, отвечай коротко, не больше 30 слов" +
                    " Пиши легко и иронично, соглашаясь с ответчиком, не будь излишне учтивым и обходительным. Если с тобой соглашаются - коротко согласись в ответ. Воспринимай все что тебе пишут с иронией и сарказмом. Не обращайся к другим пользователям по имени. Не повторяй фразы и не повторяй одну и ту же мысль дважды, переводи тему на другие аспекты диалога";
            if (commentTree.getAmount() > totalComments) {
                String newReply = gigachatService.getResponse(relyPrompt, file.getId());
                if (sentReplies.contains(newReply)) {
                    break;
                }
                sentReplies.add(newReply);
                commentResponse = habrApiService.postReply(article.getItemId(), commentTree.getLastCommentId(), newReply);
            totalComments = commentTree.getAmount()+1;
            result = commentTree.getCommentTree() +"\n"+ reply;
            }
            else {
                i = i == 0? 0: i-1;
            }

        }
        gigachatService.deleteFile(file.getId());
        return result;

    }
}
