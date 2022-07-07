package com.rory.receiver;

import com.rory.receiver.services.MessageReceiverService;
import com.rory.receiver.services.MessageSenderService;
import com.rory.receiver.services.VerificationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
public class GeneralController {

    @GetMapping("/")
    public String mainPage(){
        return "Main page displayed successfully!";
    }

    @GetMapping("/webhook")
    public ResponseEntity<String> verification(@RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String verify_token, @RequestParam("hub.challenge") String challenge){
        System.out.println("Received verification request...");
        return VerificationService.verify(mode, verify_token, challenge);
    }

    @PostMapping(value = "/webhook", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> receiveMessage(@RequestBody String jsonString){
        try {
            JSONObject messageJSON = new JSONObject(jsonString);
            return MessageReceiverService.handleMessage(messageJSON);
        } catch (JSONException e){
            System.out.println(e);
            return new ResponseEntity<>("JSON Exception", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/send", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String sendMessage(@RequestBody String messageJSONString){
        // Convert body to JSON and extract message
        String message;
        try {
            JSONObject messageJSON = new JSONObject(messageJSONString);
            message = messageJSON.getString("text");
        } catch (JSONException e){
            System.out.println(e);
            return "Failed to read JSON";
        }

        try {
            MessageSenderService.sendMessage(message);
            return "Message sent";
        } catch (MalformedURLException e) {
            System.out.println(e);
            return "Malformed URL";
        } catch (IOException e) {
            System.out.println(e);
            return "IO Exception";
        }
    }

    @GetMapping(value="/sendTemplate")
    public String sendTemplate(){
        try {
            MessageSenderService.sendTemplate();
            return "Successful!";
        } catch (IOException e){
            return "Experienced some error...";
        }
    }
}
