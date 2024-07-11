package com.example.ubs;

import java.util.Random;
import java.util.Scanner;

public class RandomGenerate {
    public static void main(String[] args) {
        // Generate a single random number
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;

        // Display the generated random number
        System.out.println("Generated random number: " + randomNumber);

        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Get input from the user
        System.out.println("Enter a number:");
        int userInput = scanner.nextInt();

        // Check if the input matches the generated random number
        if (userInput == randomNumber) {
            System.out.println("Congratulations! Your number matches the generated random number.");
            // Code to open the next HTML page goes here
        } else {
            System.out.println("Sorry, your number does not match the generated random number.");
        }

        // Close the scanner
        scanner.close();
    }
}
