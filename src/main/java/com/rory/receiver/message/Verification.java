package com.rory.receiver.message;

public class Verification {
    public String mode;
    public int challenge;
    public String verify_token;

    public Verification(String mode, int challenge, String verify_token){
        this.mode = mode;
        this.challenge = challenge;
        this.verify_token = verify_token;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "mode='" + mode + '\'' +
                ", challenge=" + challenge +
                ", verify_token='" + verify_token + '\'' +
                '}';
    }
}