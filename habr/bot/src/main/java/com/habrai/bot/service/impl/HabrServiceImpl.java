package com.habrai.bot.service.impl;

import com.habrai.bot.pojo.CommentRequest;
import com.habrai.bot.pojo.HabrComment;
import com.habrai.bot.pojo.HabrCommentTreeResponse;
import com.habrai.bot.pojo.HabrCommentResponse;
import com.habrai.bot.pojo.HabrArticle;
import com.habrai.bot.pojo.TextContent;
import com.habrai.bot.pojo.gigachat.HabrCommentsPageArticle;
import com.habrai.bot.service.IHabrApiService;
import com.habrai.bot.service.habrParser.IHabrParserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabrServiceImpl implements IHabrApiService {
    private static final SecureRandom random = new SecureRandom();

    private final IHabrParserService habrParserService;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public HabrArticle getArticle(String requestUrl) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "PostmanRuntime/7.44.0");
        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Connection", "keep-alive");

        String articleHtml = restTemplate.getForObject(requestUrl, String.class);

        return habrParserService.parseArticle(articleHtml);
    }


    @Override
    public HabrCommentResponse postReply(String articleId, String parentCommentId, String commentText) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "application/json, text/plain, */*");
        headers.add("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Content-Type", "application/json");
//        headers.add("Cookie", "_ym_uid=162748304969702155; habr_uuid=hU31kejZO3obpCB3vtcqdJHbntRAq8ON6J9M5nXGj%2F%2BrQV4v7EytqhHprBi9OCY%2F1zlHxig3Nmb%2BmVCQwuKxHw; _ga_EB19KZQJXZ=GS1.1.1720003106.1.0.1720003106.60.0.0; _ym_d=1734379709; hl=ru; fl=ru; _gcl_au=1.1.202052955.1747383133; habrsession_id=habrsession_id_9c2852fc6c4f7bc48fb086d21d48637d; _ga_2KY4NGC881=GS2.2.s1748616178$o1$g0$t1748616178$j60$l0$h0; s61687262000a=sZQTsrY5B4dfGJHfZhDQq0Wagrz8JAMVfnpSMimrztYDV7ferFzAcwmvCS184DE6; PHPSESSID=d7u3h0oghkb6m2pgj9a2v995pa; hsec_id=e468b32ba029307424c0e54ddb2ff234; habr_web_ga_uid=00f5d48a5abb89bfeefe59ae7e8ec875; habr_web_hide_ads=false; habr_web_user_id=4279952; connect_sid=s%3AmVJ7bkDXKRUJEnnd-HcODw84Cs3H1NFF.Gt9X3Dwt7eKQiegcH16rvCPDfkGLz9HFO9zesKmK%2BAY; _ga=GA1.1.594030604.1686303718; habr_web_home_feed=/feed/; visited_articles=917774:917984:917740:916266:862334; _ym_isad=2; _ga_S28W1WC23F=GS2.1.s1749986238$o179$g1$t1749986250$j48$l0$h1948814744");
        headers.add("Cookie", "_ym_uid=162748304969702155; habr_uuid=hU31kejZO3obpCB3vtcqdJHbntRAq8ON6J9M5nXGj%2F%2BrQV4v7EytqhHprBi9OCY%2F1zlHxig3Nmb%2BmVCQwuKxHw; _gcl_au=1.1.202052955.1747383133; _ym_d=1750189890; target-minor-version=251; habrsession_id=habrsession_id_d13adab639d68422dccd0c5c6f9abea6; _ga_P06JHZ9Z13=GS2.1.s1752866190$o2$g1$t1752866416$j60$l0$h0; _gid=GA1.2.1043718176.1752952570; visited_articles=929124:928586:929102:928942:923174:922806:917774:917984:917740:916266; _ga_EB19KZQJXZ=GS2.1.s1752963009$o3$g0$t1752963009$j60$l0$h0; _ym_isad=2; _ga_QM54WTELTS=GS2.1.s1753001016$o3$g0$t1753001018$j58$l0$h0; hl=ru; fl=ru; habr_web_home_feed=/feed/; s61687262000a=cjk5toN6F6QV6L4NAa2bALbwM8qxTnC8XnBk0acG9MXQ7Ek93UgMVXjvW9N2aCIg; PHPSESSID=hsuv1uel75tji6939odhonah33; hsec_id=69e81689f8ddef5572a1ce7ee0e8f578; habr_web_ga_uid=85fd09a988d203ef02b56166ac97ac54; habr_web_hide_ads=false; habr_web_user_id=5163887; connect_sid=s%3AJGXAtWpv81SfltqXW1hs20u1S_uLGWL-.1BEEMda%2FpPYGy%2Fccn8j20InbbpYclcjU%2Fo2UBc8az60; _ga=GA1.2.594030604.1686303718; _ga_2KY4NGC881=GS2.2.s1753001025$o3$g1$t1753002798$j60$l0$h0; _ga_S28W1WC23F=GS2.1.s1753000341$o222$g1$t1753002855$j58$l0$h1943185568");
//        headers.add("Csrf-token", "1v5zdqIZ-NkWtrjGMZIbE74seyrJHD98krlY");
        headers.add("Csrf-token", "93K4z1BT-4H1CSygPxLEevcZBj7RCQRNGIzI");
//        headers.add("Habr-User-Uuid", "hU31kejZO3obpCB3vtcqdJHbntRAq8ON6J9M5nXGj/+rQV4v7EytqhHprBi9OCY/1zlHxig3Nmb+mVCQwuKxHw");
        headers.add("Habr-User-Uuid", "hU31kejZO3obpCB3vtcqdJHbntRAq8ON6J9M5nXGj/+rQV4v7EytqhHprBi9OCY/1zlHxig3Nmb+mVCQwuKxHw");
        headers.add("Origin", "https://habr.com");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36");


//        URI postUri = null;
//        try {
//            postUri = new URI("https://habr.com/kek/v2/comments/posts/909620/add");
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
        URI postUri;
        try {
            postUri = new URIBuilder()
                    .setScheme("https")
                    .setHost("habr.com")
                    .setPath("kek/v2/comments/posts/" + articleId + "/add")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        CommentRequest comment = new CommentRequest();
        comment.setArticleId(articleId);
        comment.setIdempotenceKey(generateIdempotenceKey());
        comment.setPost(false);
        comment.setParentId(parentCommentId);
        TextContent textContent = new TextContent();
        textContent.setMarkdown(true);
        textContent.setSource(commentText);
        textContent.setEditorVersion(2);
        comment.setText(textContent);

        HttpEntity<CommentRequest> requestEntity = new HttpEntity<>(comment, headers);
        log.info(textContent.getSource());
        log.info("requestEntity = " + requestEntity);

        ResponseEntity<HabrCommentResponse> response = restTemplate.exchange(
                postUri,
                HttpMethod.POST,
                requestEntity,
                HabrCommentResponse.class
        );

        return response.getBody();

    }

    @Override
    public HabrCommentsPageArticle getCommentsPage(String requestUrl) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "PostmanRuntime/7.44.0");
        headers.add("Accept", "*/*");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Connection", "keep-alive");

        String articleHtml = restTemplate.getForObject(requestUrl, String.class);
        log.info(articleHtml);
        return habrParserService.parseCommentsPage(articleHtml);

    }

    @Override
    public HabrCommentTreeResponse getCommentTree(String parentCommentId, HabrCommentsPageArticle article) {
       log.info(article.getComments().toString());
        List<HabrComment> result = new ArrayList<>();

        int amount = 0;
        // Ищем корневой комментарий
        for (HabrComment comment : article.getComments()) {
            if (comment.getId().equals(parentCommentId)) {
                collectCommentAndReplies(comment, result);
                break;
            }
        }
        amount = result.size();


        StringBuilder builder = new StringBuilder();

        for (HabrComment comment : result) {
            builder.append("Author:" + comment.getAuthor() + "\n");
            builder.append("commentId:" + comment.getId() + "\n");
            builder.append("parentId:" + comment.getParentId() + "\n");
            builder.append(comment.getText());
            builder.append("\n");
            builder.append("\n");
        }

        HabrCommentTreeResponse response = new HabrCommentTreeResponse();
        response.setCommentTree(builder.toString());
        response.setLastCommentId(result.get(result.size() - 1).getId());
        response.setAmount(amount);

        return response;
    }

    private void collectCommentAndReplies(HabrComment comment, List<HabrComment> result) {
        result.add(comment);

        if (comment.getReplies() != null && !comment.getReplies().isEmpty()) {
            for (HabrComment reply : comment.getReplies()) {
                collectCommentAndReplies(reply, result);
            }
        }
    }

    public static String generateIdempotenceKey() {
        // Генерируем 16 случайных байт
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);

        // Кодируем в Base64 и удаляем невалидные символы
        String base64 = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
        return base64.substring(0, 21);
    }

}
