package com.angelbroking.smartapi.orderupdate;

import com.angelbroking.smartapi.Routes;
import com.angelbroking.smartapi.smartstream.models.SmartStreamError;
import com.angelbroking.smartapi.utils.Utils;
import com.neovisionaries.ws.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class OrderUpdateWebsocket {
    private static final int PING_INTERVAL = 10000; // 10 seconds
    private static final String HEADER = "Authorization";
    private final Routes routes = new Routes();
    private final String wsuri = routes.getSmartStreamWSURI();
    private WebSocket ws;
    private String accessToken;
    private final OrderUpdateListner orderUpdateListner;

    private Timer pingTimer;
    private LocalDateTime lastPongReceivedTime = LocalDateTime.now();

    public OrderUpdateWebsocket(String accessToken, OrderUpdateListner orderUpdateListner) {
        if (Utils.isEmpty(accessToken) || Utils.validateInputNullCheck(orderUpdateListner)) {
            throw new IllegalArgumentException(
                    "clientId, feedToken and SmartStreamListener should not be empty or null");
        }
        this.accessToken = accessToken;
        this.orderUpdateListner = orderUpdateListner;

        init();
    }

    private void init() {
        try {
            log.info("inside websocket init");
            log.info("accessToken {} ", accessToken);
            log.info("wsuri = {}", wsuri);
            ws = new WebSocketFactory()
                    .setVerifyHostname(false)
                    .createSocket(wsuri)
                    .setPingInterval(PING_INTERVAL);
            ws.addHeader(HEADER, "Bearer " + accessToken);
            ws.addListener(getWebsocketAdapter());
        } catch (IOException e) {
            if (Utils.validateInputNotNullCheck(orderUpdateListner)) {
                orderUpdateListner.onError(getErrorHolder(e));
            }
        }
    }

    private WebSocketListener getWebsocketAdapter() {
        return new WebSocketAdapter() {
            @Override
            public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws WebSocketException {
                orderUpdateListner.onConnected();
//                startPingTimer(websocket);
            }

            @Override
            public void onTextMessage(WebSocket websocket, String text) throws Exception {
                log.info("onTextMessage - {}", text);
//                orderUpdateListner.onTextMessage(text);
            }

        };
    }

    private void startPingTimer(final WebSocket websocket) {

        pingTimer = new Timer();
        pingTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    LocalDateTime currentTime = LocalDateTime.now();
                    if (lastPongReceivedTime.isBefore(currentTime.minusSeconds(20))) {
                        websocket.disconnect();
                        reconnectAndResubscribe();
                    }
                } catch (Exception e) {
                    orderUpdateListner.onError(getErrorHolder(e));
                }
            }
        }, 5000, 5000); // run at every 5 second
    }

    private void reconnectAndResubscribe() throws WebSocketException {
        log.info("reconnectAndResubscribe - started");
        init();
        connect();
        // resubscribing the existing tokens as per the mode
//        tokensByModeMap.forEach((mode,tokens) -> {
//            subscribe(mode, tokens);
//        });
        log.info("reconnectAndResubscribe - done");
    }

    private SmartStreamError getErrorHolder(Exception e) {
        SmartStreamError error = new SmartStreamError();
        error.setException(e);
        return error;
    }

    public void connect() throws WebSocketException {
        ws.connect();
        log.info("connected to uri: {}", wsuri);
    }
}
