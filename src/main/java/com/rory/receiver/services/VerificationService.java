package com.rory.receiver.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    private static final String verify_token = "rozza";

    public static ResponseEntity<String> verify(String mode, String verify_token, String challenge){
        // Check all fields are present
        if (mode != null && verify_token != null && challenge != null){
            // Check that mode and verification token are correct
            if (mode.equals("subscribe") && verify_token.equals(VerificationService.verify_token)){
                System.out.println("Verification successful.");
                return new ResponseEntity<>(challenge, HttpStatus.OK);
            } else {
                System.out.println("Verification failed: mode and/or verification token invalid.");
                return new ResponseEntity<>("Verification failed. Check verify token.", HttpStatus.FORBIDDEN);
            }
        } else {
            System.out.println("Verification failed. Not all required fields are present.");
            return new ResponseEntity<>("Verification failed. Check all fields are present.", HttpStatus.FORBIDDEN);
        }
    }
}
