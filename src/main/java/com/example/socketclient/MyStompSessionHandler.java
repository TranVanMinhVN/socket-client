package com.example.socketclient;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.CountDownLatch;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private final CountDownLatch latch;

    public MyStompSessionHandler(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {

        System.out.println("New session established : " + session.getSessionId());
//        connectedHeaders.add("API_KEY", ">8%-le~:$dwauZ.#PqDSSLuTsN'[DUK.i@{VZR$h9sDx7\"a)nTw>q]HklXeA&mm=");


        session.subscribe("/topic/joining/74AD6FD3-D8B9-E911-80BD-0CC47A96AAF3", this);
//        System.out.println("Subscribed to /topic/messages");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.out.println("Got an exception"+ exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Object.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println(payload);
//        Greeting msg = (Greeting) payload;
//        System.out.println("Received : " + msg.getContent() + " from : " );
    }

    /**
     * A sample message instance.
     * @return instance of <code>Message</code>
     */
    private Message getSampleMessage() {
        Message msg = new Message();
        msg.setFrom("Nicky");
        msg.setText("Howdy!!");
        msg.setApiKey("abcxyz");
        return msg;
    }
}
