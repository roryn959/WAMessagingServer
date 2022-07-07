package com.rory.receiver.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiverService {

    public static ResponseEntity<String> handleMessage(JSONObject jsonObject){
        JSONArray messages = jsonObject.getJSONArray("entry").getJSONObject(0).getJSONArray("changes").getJSONObject(0).getJSONObject("value").getJSONArray("messages");

        for (int i=0; i<messages.length(); i++){
            JSONObject message = messages.getJSONObject(i);
            JSONObject messageTextObject = message.getJSONObject("text");
            System.out.println(messageTextObject.getString("body"));
        }
        return new ResponseEntity<>("Message Received", HttpStatus.OK);
    }
}
