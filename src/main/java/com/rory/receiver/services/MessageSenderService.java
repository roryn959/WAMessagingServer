package com.rory.receiver.services;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

@Service
public class MessageSenderService {

    private static final String fromNumberID = "109606135123188";
    private static final String urlString = "https://graph.facebook.com/v13.0/" + fromNumberID + "/messages";

    private static final String toNumber = "919148506961";

    public static void sendMessage(String message) throws IOException {

        // Establish connection
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Add headers
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
        connection.setRequestProperty(HttpHeaders.AUTHORIZATION, "Bearer " + AccessTokenHolder.access_token);

        // Build body
        JSONObject messageJSON = new JSONObject();
        messageJSON.put("messaging_product", "whatsapp");
        messageJSON.put("to", "919148506961");

        JSONObject textJSON = new JSONObject();
        textJSON.put("body", message);

        messageJSON.put("text", textJSON);

        // Add body
        OutputStream outStream = connection.getOutputStream();
        outStream.write(messageJSON.toString().getBytes());

        // Get result
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());
    }

    public static void sendTemplate() throws IOException {

        // Establish connection
        URL url = new URL("https://graph.facebook.com/v13.0/109606135123188/messages");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Add headers
        connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");
        connection.setRequestProperty(HttpHeaders.AUTHORIZATION, "Bearer " + AccessTokenHolder.access_token);

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
