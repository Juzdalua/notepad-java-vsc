package com.notepad.notepadjavavsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notepad.notepadjavavsc.common.dto.MessagePayloadDto;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(simpleWebSocketHandler(), "/").setAllowedOrigins("*");
  }

  @Bean
  public WebSocketHandler simpleWebSocketHandler() {
    return new TextWebSocketHandler() {
      @Override
      protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("MessageStr: " + payload);

        MessagePayloadDto jsonMessage = objectMapper.readValue(payload, MessagePayloadDto.class);
        System.out.println("name:" + jsonMessage.getName() + " / age: " + jsonMessage.getAge());
        System.out.println(String.format("name: %s / age: %d", jsonMessage.getName(), jsonMessage.getAge()));

        session.sendMessage(new TextMessage("Echo: " + payload));
      }
    };
  }
}
