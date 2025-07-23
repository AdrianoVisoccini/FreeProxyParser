package com.habrai.bot.pojo.gigachat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class FileResponse {
    String id;
    String object;
    int bytes;
    String access_policy;
    int created_at;
    String filename;
    String purpose;
}
