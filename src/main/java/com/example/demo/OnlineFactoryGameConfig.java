package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
public class OnlineFactoryGameConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker (MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/factory");
    }

    @Override
    public void registerStompEndpoints (StompEndpointRegistry registry) {
        registry.addEndpoint("/online-factory-ws-endpoint").setAllowedOrigins("http://localhost:8080").withSockJS();
    }
}
