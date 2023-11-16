import java.io.IOException;
import java.util.Scanner;

public class MainRunner {
    private static Scanner terminalInput;

    public static void main(String[] args) {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = "WordList.txt";
        }


        // Initialize dependencies
        HangmanGameFrontEnd game = new HangmanGameFrontEnd(filePath);
        terminalInput = new Scanner(System.in);

        // Run the game
        boolean gameIsOver;
        while (true) {
            printGuessEntryPrompt();
            String inputGuesses = terminalInput.next();
            tryToClearTerminal();

            // Run a play for each guess typed into the terminal
            for (int i = 0; i < inputGuesses.length(); i++) {
                gameIsOver = game.makePlay(inputGuesses.charAt(i));

                if (gameIsOver) {
                    game.printGameOverMessage();
                    if (checkPlayAgain()) {
                        game = new HangmanGameFrontEnd(filePath);
                        break;
                    } else {
                        return;
                    }
                }
            }

            game.printCurrentGameStats();
        }
    }

    private static boolean checkPlayAgain() {
        System.out.print("Would you like to play again? (Type \"Y\" for yes, any other character for no.): ");
        String inputAnswer = terminalInput.next();

        if (inputAnswer.equalsIgnoreCase("Y")) {
            System.out.println();
            return true;
        } else {
            System.out.println("Thank you for playing!!");
            return false;
        }
    }

    private static void printGuessEntryPrompt() {
        System.out.println();
        System.out.print("Enter your guess: ");
    }

    private static void tryToClearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // This is bad practice, but if this fails, I'm not too worried.
            // System.out.println("Attempt to clear terminal failed: " + e.getMessage());
        }
    }

}
