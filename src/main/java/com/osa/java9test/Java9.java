package com.osa.java9test;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;

public class Java9 {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpResponse<String> response = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.SAME_PROTOCOL)
                .build()
                .send(
                        HttpRequest.newBuilder(URI.create("https://www.google.pl"))
                                .header("User-Agent", "Java 9")
                                .header("Accept", "text/html; charset=UTF-8")
                                .GET()
                                .build(),
                        HttpResponse.BodyHandler.asString());
        System.out.println(response.body());
    }
}
