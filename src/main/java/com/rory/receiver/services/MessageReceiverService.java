package com.rory.receiver.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiverService {

    public static void temp(JSONObject jsonObject){
        JSONArray entryArray = jsonObject.getJSONArray("entry");
        JSONObject entryObject = entryArray.getJSONObject(0);
        JSONArray changesArray = entryObject.getJSONArray("changes");
        JSONObject changesObject = changesArray.getJSONObject(0);
        JSONObject valueObject = changesObject.getJSONObject("value");
        JSONArray messagesArray = valueObject.getJSONArray("messages");

        // Print each message in payload
        for (int i=0; i< messagesArray.length(); i++){
            JSONObject currentMessage = messagesArray.getJSONObject(i);
            JSONObject currentMessageText = currentMessage.getJSONObject("text");
            String currentMessageTextBody = currentMessageText.getString("body");
            System.out.println(currentMessageTextBody);
        }
    }

    public static ResponseEntity<String> handleMessage(JSONObject jsonObject){
        JSONArray messages = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("changes").getJSONObject(0).getJSONObject("value").getJSONArray("messages");
        for (int i=0; i<messages.length(); i++){
            JSONObject message = messages.getJSONObject(i);
            System.out.println(message.getString("body"));
        }
        return new ResponseEntity<>("Message Received", HttpStatus.OK);
    }
}
