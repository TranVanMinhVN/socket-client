package com.example.socketclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class SocketClientApplicationTests {

    private static String URL = "ws://localhost:8080/ws";

    @Test
    void contextLoads() throws InterruptedException, ExecutionException, TimeoutException {

        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        CountDownLatch latch = new CountDownLatch(1);

        StompSessionHandler sessionHandler = new MyStompSessionHandler(latch);

        StompSession stompSession = stompClient.connect(URL, sessionHandler).get();

        System.out.println("Message sent to websocket server");
        stompSession.send("/app/hello", getSampleMessage());

    }

    private static Message getSampleMessage() {
        Message msg = new Message();
        msg.setFrom("Nicky");
        msg.setText("Howdy!!");
        msg.setApiKey("abcxyz");
        return msg;
    }

}
