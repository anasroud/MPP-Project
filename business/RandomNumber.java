package business;

import java.util.Random;

public class RandomNumber {
    public static void main(String[] args) {
        int randomFourDigitNumber = generateRandomFourDigitNumber();
        System.out.println("Random Four-Digit Number: " + randomFourDigitNumber);
    }

    public static int generateRandomFourDigitNumber() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Generates a random number between 1000 and 9999 (inclusive).
    }
}
