package com.habrai.bot.pojo.gigachat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthGigachatREsponse {

    String access_token;
    String expires_at;
}
