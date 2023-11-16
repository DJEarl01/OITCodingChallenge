//Front end implementation of the Hangman Game
public class HangmanGameFrontEnd implements HangmanGameFrontEndObserver {
    private final HangmanGameBackEnd gameLogic;

    public HangmanGameFrontEnd(String dictionaryFileName) {
        gameLogic = new HangmanGameBackEnd(this, dictionaryFileName);

        // Print out welcome message...
        System.out.println("Welcome to Hangman!");
        System.out.println("There are " + gameLogic.getPickedWordLength() + " characters in your word. Good Luck!");
    }

    // The return value of this function is whether the game is over.
    public boolean makePlay(Character guess) {
        return gameLogic.makePlayLogic(guess);
    }

    public void printGameOverMessage() {
        System.out.println();
        System.out.println("Game Over!");
        System.out.println("The word was " + gameLogic.getPickedWord());
        System.out.println("You took " + gameLogic.getGuessCount() + " total guesses.");
    }

    public void printCurrentGameStats() {
        System.out.println();
        System.out.println("Total Guesses: " + gameLogic.getGuessCount());
        System.out.println("Total Correct Guesses: " + gameLogic.getCorrectGuessCount());
        System.out.println("Total Incorrect Guesses: " + gameLogic.getIncorrectGuessCount());
        System.out.println();
        System.out.println("Guessed Letters (Not in the word): " + gameLogic.getBadGuessedLetters());
        System.out.println("Word So Far: " + gameLogic.getWordSoFar());
        System.out.println("Hangman:");
        printStickFigure();
    }

    private void printStickFigure() {
        int incorrectGuesses = gameLogic.getIncorrectGuessCount();

        System.out.println("_____________");
        switch(incorrectGuesses) {
            case 0:
                System.out.println("      |\n\n\n\n\n\n\n\n");
                break;
            case 1:
                System.out.println("      |\n      O\n\n\n\n\n\n\n");
                break;
            case 2:
                System.out.println("      |\n      O\n      |\n\n\n\n\n\n");
                break;
            case 3:
                System.out.println("      |\n      O\n     /|\n\n\n\n\n");
                break;
            case 4:
                System.out.println("      |\n      O\n     /|\\\n\n\n\n");
                break;
            case 5:
                System.out.println("      |\n      O\n     /|\\\n      |\n\n\n");
                break;
            case 6:
                System.out.println("      |\n      O\n     /|\\\n      |\n     /\n\n");
                break;
            case 7:
                System.out.println("      |\n      O\n     /|\\\n      |\n     / \\\n\n");
                break;
            case 8:
                System.out.println("      |\n      O\n     /|\\\n      |\n     / \\\n    =\n");
                break;
            case 9:
                System.out.println("      |\n      O\n     /|\\\n      |\n     / \\\n    =   =\n");
                break;
            default:
                System.out.println("      |\n      X\n     /|\\\n      |\n     / \\\n    =   =\nThis Boi Dead");
                break;
        }
    }

    // ------------ Begin Observer Implementations --------------

    @Override
    public void printGuessAlreadyMade(Character guess) {
        System.out.println("The letter " + guess + " has already been guessed!");
    }

    @Override
    public void printCorrectGuess(Character guess) {
        System.out.println("The letter " + guess + " is in the word!");
    }

    @Override
    public void printIncorrectGuess(Character guess) {
        System.out.println("The letter " + guess + " is not in the word.");
    }
}
