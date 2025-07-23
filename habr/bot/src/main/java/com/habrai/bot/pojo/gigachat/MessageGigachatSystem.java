package com.habrai.bot.pojo.gigachat;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessageGigachatSystem extends MessageGigachat {

    private List<String> attachments;

    public MessageGigachatSystem(String role, String content, List<String> attachments) {
        super(role, content);
        this.attachments = attachments;
    }


}
