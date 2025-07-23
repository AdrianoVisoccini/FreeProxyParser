package com.adrianovisoccini.proxyparser.services;

import com.adrianovisoccini.proxyparser.dto.ServerDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeproxyupdateParserService {

    RestTemplate restTemplate = new RestTemplate();


    private static final String SOCKS5_PROXY_URL = "https://freeproxyupdate.com/socks5-proxy";
    private static final String HTTP_PROXY_URL = "https://freeproxyupdate.com/http-proxy";
    private static final String SOCKS4_PROXY_URL = "https://freeproxyupdate.com/socks4-proxy";


    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public List<ServerDto> getSocks5ProxyList() {

        String response = restTemplate.getForObject(SOCKS5_PROXY_URL, String.class);

        return parseResponseHtml(response);

    }

    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public List<ServerDto> getHttpProxyList() {

        String response = restTemplate.getForObject(HTTP_PROXY_URL, String.class);

        return parseResponseHtml(response);
    }

    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public List<ServerDto> getSocks4ProxyList() {

        String response = restTemplate.getForObject(SOCKS4_PROXY_URL, String.class);

        return parseResponseHtml(response);
    }



    private List<ServerDto> parseResponseHtml(String response) {
        List<ServerDto> servers = new ArrayList<>();

        Document doc = Jsoup.parse(response);

        Elements rows = doc.select("tr");

        for (Element row : rows) {
            Elements cells = row.select("td");

            ServerDto server = new ServerDto();
            if (cells.size() >= 4) {
                server.setIp(cells.get(0).text());
                server.setPort(cells.get(1).text());
                server.setProtocol(cells.get(3).text());

                servers.add(server);
            }
        }
        return servers;
    }
}
