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
                "\"playerId\":\"966dc205-7697-4972-94ca-4e343f1a398e\", " +
                "\"action\":\"right\", " +
                "\"duration\":5" +
                "}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();
        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println("Response status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

