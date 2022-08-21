package com.ssafy.CommonPJT.config;


import com.ssafy.CommonPJT.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class socketConfig implements WebSocketConfigurer {
    private final Handler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "ws/socket").setAllowedOrigins("*").addInterceptors(new HttpSessionHandshakeInterceptor());;
    }
}
