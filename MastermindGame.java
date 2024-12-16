import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MastermindGame {

    // The available colors for guessing
    static String[] colors = {"red", "blue", "yellow", "green", "orange", "pink"};

    public static void main(String[] args) {
        System.out.println("Welcome to Mastermind Game!");
        startGame();
    }
    
    static void startGame() {
        Scanner myObj = new Scanner(System.in);
        int maxAttempts = 13;
        
        String[] correctColors = generateRandomCode();

        for (int i = 0; i < maxAttempts; i++) {
            System.out.println("Attempt " + (i + 1) + " of " + maxAttempts);
            String[] playerGuess = new String[5];
            boolean validInput = true;

            for (int j = 0; j < 5; j++) {
                System.out.println("Enter color " + (j + 1) + ": ");
                String color = myObj.nextLine().toLowerCase();
                
                if (Arrays.asList(colors).contains(color)) {
                    playerGuess[j] = color;
                } else {
                    System.out.println("Invalid color! Please choose from: red, blue, yellow, green, orange, pink.");
                    validInput = false;
                    break;
                }
            }

            if (!validInput) {
                i--;
                continue;
            }

            String feedback = giveFeedback(playerGuess, correctColors);
            System.out.println("Feedback: " + feedback);
            
            if (feedback.equals("Correct! You win!")) {
                break;
            }
        }
        
        System.out.println("Game Over.");
    }

    static String[] generateRandomCode() {
        List<String> colorList = Arrays.asList(colors);
        Collections.shuffle(colorList);
        String[] randomCode = new String[5];

        for (int i = 0; i < 5; i++) {
            randomCode[i] = colorList.get(i);
        }
        
        System.out.println("The secret code has been generated. Start guessing!");
        return randomCode;
    }
    
    static String giveFeedback(String[] playerGuess, String[] correctColors) {
        int whiteCount = 0;
        int blackCount = 0;

        boolean[] correctUsed = new boolean[5];
        boolean[] guessUsed = new boolean[5];
        
        for (int i = 0; i < 5; i++) {
            if (playerGuess[i].equals(correctColors[i])) {
                whiteCount++;
                correctUsed[i] = true;
                guessUsed[i] = true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (!guessUsed[i]) {
                for (int j = 0; j < 5; j++) {
                    if (!correctUsed[j] && playerGuess[i].equals(correctColors[j])) {
                        blackCount++;
                        correctUsed[j] = true;
                        break;
                    }
                }
            }
        }

        if (whiteCount == 5) {
            return "Correct! You win!";
        } else {
            return whiteCount + " white(s), " + blackCount + " black(s)";
        }
    }
}
