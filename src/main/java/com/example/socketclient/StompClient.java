package com.example.socketclient;


import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class StompClient {

    private static String URL = "ws://localhost:8080/ws";

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        CountDownLatch latch = new CountDownLatch(1);
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        StompHeaders connectHeaders = new StompHeaders();
        connectHeaders.add("API_KEY", "> 8%-le~:$dwauZ.#PqDSSLuTsN'[DUK.i@{VZR$h9sDx7\"a)nTw>q]HklXeA&mm=");
        stompClient.connect(URL, new WebSocketHttpHeaders(), connectHeaders, new MyStompSessionHandler(latch));
        latch.await();

        new Scanner(System.in).nextLine(); // Don't close immediately.
    }

    private static Message getSampleMessage() {
        Message msg = new Message();
        msg.setFrom("Nicky");
        msg.setText("Howdy!!");
        msg.setApiKey("abcxyz");
        return msg;
    }
}