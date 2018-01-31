package com.osa.java9test;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.CookieManager;
import java.net.ProxySelector;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.Executors;

public class Java9 {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.SAME_PROTOCOL)
                .proxy(ProxySelector.getDefault())
                .version(HttpClient.Version.HTTP_2)
                .executor(Executors.newCachedThreadPool())
                .priority(256)
                .cookieManager(new CookieManager())
//                .authenticator(new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("user", "passwd".toCharArray());
//                    }
//                })
                .build();

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.pgs-soft.com/pl/"))
                .header("User-Agent", "Java 9")
                .header("Accept", "text/html; charset=UTF-8")
                .timeout(Duration.ofSeconds(5))
//                                .POST(HttpRequest.BodyProcessor.from)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandler.asString());
        System.out.println(response.body());
    }
}
