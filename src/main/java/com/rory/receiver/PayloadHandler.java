package com.rory.receiver;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class PayloadHandler {

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
}
