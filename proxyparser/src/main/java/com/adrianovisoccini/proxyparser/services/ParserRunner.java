package com.adrianovisoccini.proxyparser.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParserRunner implements CommandLineRunner {

    ProxyListFileGenerationService proxyListFileGenerationService = new ProxyListFileGenerationService();
    @Override
    public void run(String... args) throws Exception {

        proxyListFileGenerationService.createProxyList();
    }
}
