package com.example.ubs;

public class OTPVerifier {
    public static boolean verifyOTP(String enteredOTP, String generatedOTP) {
        return enteredOTP != null && enteredOTP.equals(generatedOTP);
    }
}
