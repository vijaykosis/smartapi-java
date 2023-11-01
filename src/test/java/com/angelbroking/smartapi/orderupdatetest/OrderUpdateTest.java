package com.angelbroking.smartapi.orderupdatetest;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.orderupdate.OrderUpdateListner;
import com.angelbroking.smartapi.orderupdate.OrderUpdateWebsocket;
import com.angelbroking.smartapi.smartstream.models.SmartStreamError;
import com.neovisionaries.ws.client.WebSocketException;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class OrderUpdateTest {
    private static String clientID;
    private static String clientPass;
    private static String apiKey;
    private static String feedToken;
    private static String totp;

    @BeforeAll
    public void init(){
        clientID = "V53536308";
        clientPass = "1618";
        apiKey = "sTqSAb7f";
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        String totp_key = "C24L62RAZDYQH5ZGXJVWTW5QQA";
        totp = String.valueOf(gAuth.getTotpPassword(totp_key));

        SmartConnect smartConnect = new SmartConnect(apiKey);
        User user = smartConnect.generateSession(clientID, clientPass, totp);
        System.out.println("user = " + user);
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());

        feedToken = user.getFeedToken();
    }

    @Test
    public void testOrderUpdate_success() throws WebSocketException {
        clientID = "V53536308";
        clientPass = "1618";
        apiKey = "sTqSAb7f";
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        String totp_key = "C24L62RAZDYQH5ZGXJVWTW5QQA";
        totp = String.valueOf(gAuth.getTotpPassword(totp_key));

        SmartConnect smartConnect = new SmartConnect(apiKey);
        User user = smartConnect.generateSession(clientID, clientPass, totp);
        System.out.println("user = " + user);
        smartConnect.setAccessToken(user.getAccessToken());
        smartConnect.setUserId(user.getUserId());

        feedToken = user.getFeedToken();
        System.out.println("feedToken = " + feedToken);
        String accessToken = user.getAccessToken();
        System.out.println("accessToken = " + accessToken);
        OrderUpdateWebsocket orderUpdateWebsocket = new OrderUpdateWebsocket(feedToken, new OrderUpdateListner() {
            @Override
            public void onConnected() {
                System.out.println("Connected");
            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onError(SmartStreamError error) {

            }

            @Override
            public void onPong() {

            }
        });

        orderUpdateWebsocket.connect();
    }
}
