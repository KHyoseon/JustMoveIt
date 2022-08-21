package com.ssafy.CommonPJT.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;

@Component
@Log4j2
public class Handler extends TextWebSocketHandler {
    private ArrayList<WebSocketSession> sessions;

    public Handler() {
        this.sessions = new ArrayList<>();
    }

    public Handler(ArrayList<WebSocketSession> clients) {
        this.sessions = clients;
    }

    /* Client가 접속 시 호출되는 메서드 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session + " 클라이언트 접속");
        sessions.add(session);
        session.sendMessage(new TextMessage("all_connected"));
//        if(sessions.size()>=2){
//            for(WebSocketSession sess: sessions){
//                sess.sendMessage(new TextMessage("all_connected"));
//            }
//        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload : " + payload);

        // payload: 다른 세션한테 정보 요청
        if(payload.equals("request")){
            for(WebSocketSession other: sessions){
                if(!other.getId().equals(session.getId())){
                    other.sendMessage(new TextMessage("request"));
                }
            }
        } else {
            // payload: 요청된 데이터 전송
            // 송신 세션에게 데이터룰 받으면 수신 세션에게 전달, 송신 세션에게 done 전송
            for (WebSocketSession other : sessions) {
                if (!other.getId().equals(session.getId())) {
                    other.sendMessage(new TextMessage(payload));
                    session.sendMessage(new TextMessage("done"));
                }
            }
        }
    }

    /* Client가 접속 해제 시 호출되는 메서드드 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("disconnected");
        sessions.remove(session);
        log.info(session + " 클라이언트 접속 해제");
    }
}
