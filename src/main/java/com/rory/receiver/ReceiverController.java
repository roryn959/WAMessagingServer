package com.rory.receiver;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


@RestController
public class ReceiverController {

    private String verify_token = "rozza";

    @GetMapping("/webhook")
    public ResponseEntity<String> verification(@RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String verify_token, @RequestParam("hub.challenge") String challenge){
        System.out.println("Received verification request...");
        // Check all necessary fields were entered
        if (mode != null && verify_token != null && challenge != null){
            // Check mode and verify token are correct
            if (mode.equals("subscribe") && verify_token.equals(this.verify_token)){
                System.out.println("Request verified");
                return new ResponseEntity<>(challenge, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Verification failed. Check verify token.", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("Verification failed. Check all fields are present.", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/webhook", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String handleMessage(@RequestBody String jsonString){

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            PayloadHandler.temp(jsonObject);
        } catch (JSONException e){
            System.out.println(e);
        }

        return "okay";
    }

    @PostMapping(value = "/send")
    public String sendMessage(@RequestBody String message){
        return "Not yet implemented";
    }
}
