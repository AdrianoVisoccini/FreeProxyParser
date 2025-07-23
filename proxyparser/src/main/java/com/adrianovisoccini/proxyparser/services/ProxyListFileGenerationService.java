package com.adrianovisoccini.proxyparser.services;

import com.adrianovisoccini.proxyparser.dto.ServerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProxyListFileGenerationService {

    FreeproxyupdateParserService freeproxyupdateParserService = new FreeproxyupdateParserService();


    @Scheduled(fixedDelay = 3600000)
    public void createProxyList() {
        List<ServerDto> resultList = new ArrayList<>();
        resultList.addAll(freeproxyupdateParserService.getSocks5ProxyList());
        resultList.addAll(freeproxyupdateParserService.getHttpProxyList());
        resultList.addAll(freeproxyupdateParserService.getSocks4ProxyList());
        createFile(resultList);
    }

    public void createFile(List<ServerDto> servers) {

        Path currentDirectory = Paths.get("").toAbsolutePath();

        Path proxyListFile = Paths.get(currentDirectory.toString(), "proxylist");

        StringBuilder proxy = new StringBuilder();
        for (ServerDto server : servers) {
            proxy.append(server.getProtocol())
                    .append("\t")
                    .append(server.getIp())
                    .append(" ")
                    .append(server.getPort())
                    .append(System.lineSeparator());
        }

        try {
            Files.write(proxyListFile, proxy.toString().getBytes());
            System.out.println("Файл успешно создан: " + proxyListFile);
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла: " + e.getMessage());
        }

    }


}
