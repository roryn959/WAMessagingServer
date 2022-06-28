package com.rory.receiver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReceiverController {

    private String verify_token = "rozza";

    @GetMapping("/webhook")
    public ResponseEntity<String> verification(@RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String verify_token, @RequestParam("hub.challenge") String challenge){
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

    @PostMapping("/webhook")
    public String handleMessage(){
        return "post";
    }
}
