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
                "\"playerId\":\"d2046bbf-183b-4b55-81be-10f19323cff0\", " +
                "\"action\":\"forward\", " +
                "\"duration\":3" +
                "}";
        String json2 = "{" +
                "\"playerId\":\"c369252b-fd19-4ea0-8ebb-0f45454c707f\", " +
                "\"action\":\"backward\", " +
                "\"duration\":5" +
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

