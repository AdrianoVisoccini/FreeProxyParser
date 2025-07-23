package com.habrai.bot.pojo.gigachat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RequestGigaChat {

    String model;

    List<MessageGigachat> messages;
}

