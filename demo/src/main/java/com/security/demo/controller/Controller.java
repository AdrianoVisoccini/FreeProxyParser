package com.security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/public")
    public String publicPage() {

        return "Эта страница в принципе доступна любому оборванцу";

    }

    @GetMapping("/secure")
    public String securePage(){
        return "Эта страница доступна только самым уважаемым господам и исключительно после прохождение аутентификации";
    }

    @GetMapping("/role")
    @PreAuthorize("hasRole('ADMIN')")
    public String rolePage(){
        return "а эта страница вообще доступна только тем господам, кто прошел аутентификацию, а так же обладает ролью ADMIN";
    }

    @GetMapping("/authorised")
    @PreAuthorize("hasAnyAuthority('READ_THAT_AUTHORISED_PAGE')")
    public String authorisedPage(){
        return "эта старница доступна госопдам, прошедшим аутентификацию, имеющим вроде обычную роль USER но при этом ограничена по AUTHORITY";
    }
    @GetMapping("/exception")
    public String exceptionPage(){
        return "кажись, у вас не достаточно правов для того чтобы посмотреть эту страницу";
    }

    @GetMapping("/oauth2")
    public String oauth2Page(){
        return "oauth2";
    }
}

