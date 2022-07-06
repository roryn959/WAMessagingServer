package com.rory.receiver.services;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MessageSenderService {

    private static final String urlStart = "https://graph.facebook.com/v13.0/";
    private static final String urlEnd = "/messages?access_token=";
    private static final String fromNumber = "109606135123188";
    private static final String toNumber = "919148506961";
    private static final String accessToken = "EAAQZBAimZBZCuUBACSewjuCuu73chXuZAJ1UQhJt9xowFi6GGWPpZA4N4ldwwGL1dY6Ih896ZBjgFIy690zluTvxac4cyqvZCtvX7AduEuzPYLum0GICmnk3nlUZB2NKZCnKw5SsUm7SZApF9WAHZCaZA7hMirQ495suNkYXrieNnDw3WTNuQZCVZCfpH0HzOMru1tt7f9FJVqesZBE0AZDZD";
    public static void sendMessage(String message) throws MalformedURLException, IOException {
        System.out.println(message);

        // Establish connection
        URL url = new URL(urlStart + fromNumber + urlEnd + accessToken);
        //URL url = new URL("https://rorytest.free.beeceptor.com");
        System.out.println(url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Add header data
        String tokenHeaderData = "Bearer " + accessToken;

        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
        connection.setRequestProperty(HttpHeaders.ACCEPT, "application/json");
        //connection.setRequestProperty(HttpHeaders.AUTHORIZATION, tokenHeaderData);

        // Build body
        JSONObject body = new JSONObject();
        body.put("messaging_product", "whatsapp");
        body.put("to", MessageSenderService.toNumber);
        //body.put("access_token", accessToken);

        JSONObject messageJSON = new JSONObject();
        messageJSON.put("body", message);
        body.put("text", messageJSON);

        // Add body
        OutputStream outStream = connection.getOutputStream();
        outStream.write(messageJSON.toString().getBytes());

        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
    }
}
