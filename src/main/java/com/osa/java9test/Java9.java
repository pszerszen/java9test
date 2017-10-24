package com.osa.java9test;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.time.Duration;

public class Java9 {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.SAME_PROTOCOL)
                .proxy(ProxySelector.getDefault())
                .version(HttpClient.Version.HTTP_1_1)
//                .authenticator(new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication("user", "passwd".toCharArray());
//                    }
//                })
                .build()
                .send(
                        HttpRequest.newBuilder(URI.create("https://www.pgs-soft.com/pl/"))
                                .header("User-Agent", "Java 9")
                                .header("Accept", "text/html; charset=UTF-8")
                                .timeout(Duration.ofSeconds(5))
//                                .POST(HttpRequest.BodyProcessor.from)
                                .GET()
                                .build(),
                        HttpResponse.BodyHandler.asString());
        System.out.println(response.body());
    }
}
