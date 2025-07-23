package com.habrai.bot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TextContent {
    private String source;

    @JsonProperty("editorVersion")
    private int editorVersion;

    @JsonProperty("isMarkdown")
    private boolean isMarkdown;

    public void setSource(String source) {
//        this.source = "{\"type\":\"doc\",\"content\":[{\"type\":\"paragraph\",\"attrs\":{\"simple\":false,\"persona\":false},\"content\":[{\"type\":\"text\",\"text\":\"" + source + "\"}]}]}";    }
        this.source = source;
    }

    public void setEditorVersion(int editorVersion) {
        this.editorVersion = editorVersion;
    }

    public void setMarkdown(boolean markdown) {
        isMarkdown = markdown;
    }
}