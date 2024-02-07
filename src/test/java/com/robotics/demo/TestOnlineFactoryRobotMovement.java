package com.robotics.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TestOnlineFactoryRobotMovement {

    public static void main(String[] args) {
        String url = "http://localhost:8080/online-factory/api/move-robot";

        /*
            playerId; //e.g. "072fb3fb-6faf-4f9b-b2cf-2c5d79701f22"
            action; // e.g., "forward", "backward", "left", "right"
            duration; // duration in miliseconds
         */
        String json = "{" +
                "\"playerId\":\"94565bc2-6154-49b8-8940-e97f9dd2fbc0\", " +
                "\"action\":\"backward\", " +
                "\"duration\":5" +
                "}";
        String json2 = "{" +
                "\"playerId\":\"0d62385d-59cb-452e-9c8d-7f1fea44938c\", " +
                "\"action\":\"left\", " +
                "\"duration\":15" +
                "}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json2))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
            response = client.send(request2, BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

