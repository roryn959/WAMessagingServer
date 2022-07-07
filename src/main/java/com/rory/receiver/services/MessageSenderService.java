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
    private static final String accessToken = "EAAQZBAimZBZCuUBAFswmFKJA7IwDLFunDnDW3v61m1iVTL9jIOCbL1DJUzZCC2Ld9NNWbRyNouzQu4bg7tM9ak5PzNCBkF38NE23jFL1iMaGYgxmZAhUWTZAZCZCmJQdH2A2Wpb1H1hknrrmzQ1BIqTtmZBGZAwH2vubLgaMZB8ldSKB2VUEaK9IlaCK99jieIqUZAZA8XCIEDw5t3gZDZD";
    public static void sendMessage(String message) throws MalformedURLException, IOException {
        System.out.println(message);

        // Establish connection
        URL url = new URL(urlStart + fromNumber + urlEnd + accessToken);
        //URL url = new URL("https://rorytest.free.beeceptor.com");
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

    public static void sendTemplate() throws IOException {

        // Establish connection
        URL url = new URL("https://rorytest.free.beeceptor.com");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Add headers
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
        connection.setRequestProperty(HttpHeaders.AUTHORIZATION, MessageSenderService.accessToken);

        // Build body
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("messaging_product", "whatsapp");
        messageJSON.put("to", "919148506961");
        messageJSON.put("type", "template");

        JSONObject templateJSON = new JSONObject();
        templateJSON.put("name", "hello_world");

        JSONObject languageJSON = new JSONObject();
        languageJSON.put("code", "en_US");

        templateJSON.put("language", languageJSON);

        messageJSON.put("template", templateJSON);

        // Add body
        OutputStream outStream = connection.getOutputStream();
        outStream.write(messageJSON.toString().getBytes());

        // Get result
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
    }
}
