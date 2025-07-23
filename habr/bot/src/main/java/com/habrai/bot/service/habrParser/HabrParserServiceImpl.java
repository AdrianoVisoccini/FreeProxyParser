package com.habrai.bot.service.habrParser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.habrai.bot.pojo.HabrArticle;
import com.habrai.bot.pojo.HabrComment;
import com.habrai.bot.pojo.gigachat.HabrCommentsPageArticle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;


import static java.lang.Integer.parseInt;

@Service
public class HabrParserServiceImpl implements IHabrParserService {

    @Override
    public HabrArticle parseArticle(String html) {
        Document doc = Jsoup.parse(html);
        HabrArticle article = new HabrArticle();

        Element titleElement = doc.selectFirst("h1.tm-title_h1");
        if (titleElement != null) {
            article.setTitle(titleElement.text());
        }

        Element authorElement = doc.selectFirst(".tm-user-info__username");
        if (authorElement != null) {
            article.setAuthor(authorElement.text());
        }

        Element dateElement = doc.selectFirst(".tm-article-datetime-published time");
        if (dateElement != null) {
            article.setPublicationDate(dateElement.attr("datetime"));
        }

        Element viewsElement = doc.selectFirst(".tm-icon-counter[title='Количество просмотров'] .tm-icon-counter__value");
        if (viewsElement != null) {
            String viewsText = viewsElement.text()
                    .replace("K", "000")
                    .replace(".", "");
            article.setViewsCount(parseInt(viewsText));
        }

        Element commentsElement = doc.selectFirst(".tm-article-comments-counter-link__value");
        if (commentsElement != null) {
            article.setCommentsCount(parseInt(commentsElement.text()));
        }

        Elements tagElements = doc.select(".tm-separated-list__title:contains(Теги:) + .tm-separated-list__list .tm-separated-list__item");
        for (Element tag : tagElements) {
            article.getTags().add(tag.text());
        }

        Elements hubElements = doc.select(".tm-separated-list__title:contains(Хабы:) + .tm-separated-list__list .tm-separated-list__item");
        for (Element hub : hubElements) {
            article.getHubs().add(hub.text());
        }

        Element contentElement = doc.selectFirst("#post-content-body");
        if (contentElement != null) {
            article.setContent(contentElement.text());
        }

        Element itemIdElement = doc.selectFirst("meta[property='aiturec:item_id']");

        if (itemIdElement != null) {

            String itemId = itemIdElement.attr("content");
            article.setItemId(itemId);
        }

            return article;
    }

        @Override
    public HabrCommentsPageArticle parseCommentsPage(String html) {
        Document doc = Jsoup.parse(html, "UTF-8");

        String title = doc.title();

        Element authorElement = doc.selectFirst(".tm-user-info__username");
        String articleAuthor = authorElement != null ? authorElement.text() : "Unknown";

        Element dateElement = doc.selectFirst("time[datetime]");
        String datePublished = dateElement != null ? dateElement.attr("datetime") : "Unknown";

        Elements commentContainers = doc.select("[data-comment-body]");

        List<HabrComment> comments = new ArrayList<>();
        Map<String, HabrComment> commentMap = new HashMap<>();

        // Сначала собираем все комментарии и кладём их в map
        for (Element container : commentContainers) {
            String id = container.attr("data-comment-body");

            // Получаем уровень вложенности из класса
            int level = 0;
            for (String cls : container.classNames()) {
                if (cls.startsWith("tm-comment-thread__indent_l-")) {
                    String levelStr = cls.replace("tm-comment-thread__indent_l-", "");
                    try {
                        level = Integer.parseInt(levelStr);
                    } catch (NumberFormatException ignored) {}
                    break;
                }
            }

            Element commentRoot = container.selectFirst(".tm-comment");
            if (commentRoot == null) continue;

            Element authorEl = container.selectFirst(".tm-user-info__username");
            String commentAuthor = authorEl != null ? authorEl.text() : "Anonymous";

            Element textEl = container.selectFirst(".tm-comment__body-content");
            String text = textEl != null ? textEl.text() : "";

            Element timeEl = container.selectFirst("time");
            String date = timeEl != null ? timeEl.attr("datetime") : "Unknown";

            int score = 0; // пока не поддерживается
            List<HabrComment> replies = new ArrayList<>();

            HabrComment comment = new HabrComment(id, "root", String.valueOf(level), commentAuthor, text, date, score, replies);
            comments.add(comment);
            commentMap.put(id, comment);
        }

            // Стек для отслеживания родителей по уровням вложенности
            Map<Integer, String> parentStack = new HashMap<>();
            parentStack.put(0, "root");

// Теперь устанавливаем parentId на основе уровня вложенности
            for (HabrComment comment : comments) {
                int level = Integer.parseInt(comment.getLevel());

                // Уровень 0 — корневой, его родитель root
                if (level == 0) {
                    comment.setParentId("root");
                    parentStack.put(level, comment.getId());
                } else {
                    // Ищем родителя на уровень ниже
                    String parentId = parentStack.getOrDefault(level - 1, "root");
                    comment.setParentId(parentId);
                    parentStack.put(level, comment.getId());
                }
            }

        // Группируем ответы
        Map<String, List<HabrComment>> replyMap = new HashMap<>();
        for (HabrComment comment : comments) {
            String parentId = comment.getParentId();
            if (!"root".equals(parentId)) {
                replyMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(comment);
            }
        }

        for (HabrComment comment : comments) {
            comment.setReplies(replyMap.getOrDefault(comment.getId(), Collections.emptyList()));
        }

        return new HabrCommentsPageArticle(title, articleAuthor, datePublished, filterRootComments(comments));
    }

    private List<HabrComment> filterRootComments(List<HabrComment> allComments) {
        return allComments.stream()
                .filter(c -> "root".equals(c.getParentId()))
                .collect(Collectors.toList());
    }
//
//    @Override
//    public HabrCommentsPageArticle parseCommentsPage(String html) {
//        // Шаг 1: Извлекаем JSON с комментариями
//        String jsonPart = extractJsonFromHtml(html);
//        if (jsonPart == null) {
//            throw new IllegalArgumentException("JSON с комментариями не найден в HTML");
//        }
//
//        // Шаг 2: Парсим JSON
//        Gson gson = new Gson();
//        JsonObject commentsRoot = gson.fromJson(jsonPart, JsonObject.class);
//        JsonObject allCommentsJson = commentsRoot.getAsJsonObject("comments");
//
//        Map<String, HabrComment> commentMap = new HashMap<>();
//        List<HabrComment> comments = new ArrayList<>();
//
//        for (Map.Entry<String, JsonElement> entry : allCommentsJson.entrySet()) {
//            JsonObject commentJson = entry.getValue().getAsJsonObject();
//
//            String id = commentJson.get("id").getAsString();
//            String parentId = "root";
//            if (commentJson.has("parentId") && !commentJson.get("parentId").isJsonNull()) {
//                parentId = commentJson.get("parentId").getAsString();
//            }
//
//            int level = commentJson.get("level").getAsInt();
//            String timePublished = commentJson.get("timePublished").getAsString();
//            String message = decodeHtmlMessage(commentJson.get("message").getAsString());
//
//            JsonObject authorJson = commentJson.getAsJsonObject("author");
//            String authorName = "Anonymous";
//            if (authorJson != null) {
//                if (authorJson.has("fullname") && !authorJson.get("fullname").isJsonNull()) {
//                    authorName = authorJson.get("fullname").getAsString();
//                } else if (authorJson.has("alias")) {
//                    authorName = authorJson.get("alias").getAsString();
//                }
//            }
//
//            int score = commentJson.has("score") ? commentJson.get("score").getAsInt() : 0;
//
//            HabrComment comment = new HabrComment(
//                    id,
//                    parentId,
//                    level,
//                    authorName,
//                    message,
//                    timePublished,
//                    score,
//                    new ArrayList<>()
//            );
//
//            comments.add(comment);
//            commentMap.put(id, comment);
//        }
//
//        // Шаг 3: Строим дерево комментариев
//        Map<String, List<HabrComment>> replyMap = new HashMap<>();
//        for (HabrComment comment : comments) {
//            if (!"root".equals(comment.getParentId())) {
//                replyMap.computeIfAbsent(comment.getParentId(), k -> new ArrayList<>()).add(comment);
//            }
//        }
//
//        for (HabrComment comment : comments) {
//            comment.setReplies(replyMap.getOrDefault(comment.getId(), Collections.emptyList()));
//        }
//
//        // Шаг 4: Получаем заголовок статьи и автора
//        Document doc = Jsoup.parse(html);
//        String title = doc.title();
//
//        Element authorElement = doc.selectFirst(".tm-user-info__username");
//        String articleAuthor = authorElement != null ? authorElement.text() : "Unknown";
//
//        Element dateElement = doc.selectFirst("time[datetime]");
//        String datePublished = dateElement != null ? dateElement.attr("datetime") : "Unknown";
//
//        // Шаг 5: Возвращаем результат
//        return new HabrCommentsPageArticle(title, articleAuthor, datePublished, filterRootComments(comments));
//    }
//
//    private String extractJsonFromHtml(String html) {
//        if (html == null || html.isEmpty()) return null;
//
//        int start = html.indexOf("\"comments\":{");
//        if (start == -1) {
//            System.err.println("Не найдено начало JSON 'comments'");
//            return null;
//        }
//
//        int depth = 0;
//        int i = start;
//
//        try {
//            while (i < html.length()) {
//                char c = html.charAt(i);
//                if (c == '{') depth++;
//                else if (c == '}') depth--;
//
//                if (depth == 0) break;
//                i++;
//            }
//
//            if (depth != 0) {
//                System.err.println("Не удалось найти конец JSON-объекта");
//                return null;
//            }
//
//            // Извлекаем JSON
//            String rawJson = html.substring(start, i + 1);
//
//            // Оборачиваем в {} чтобы сделать валидным корневым объектом
//            return String.format("{%s}", rawJson);
//        } catch (Exception e) {
//            System.err.println("Ошибка при извлечении JSON: " + e.getMessage());
//            return null;
//        }
//    }
//
//
//    private String decodeHtmlMessage(String encoded) {
//        return StringEscapeUtils.unescapeHtml4(encoded);
//    }

}
