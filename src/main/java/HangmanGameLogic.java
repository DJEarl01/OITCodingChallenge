import java.util.ArrayList;

public class HangmanGameLogic {
    private final WordDictionary dictionary;
    private final String pickedWord;
    private ArrayList<Character> guessedLetters;
    private ArrayList<Character> printedWordAfterGuesses;
    private int correctGuessCount;
    private HangmanGameFrontEndObserver observer;

    public HangmanGameLogic(HangmanGameFrontEndObserver observer, String dictionaryFileName) {
        this.observer = observer;
        correctGuessCount = 0;
        dictionary = new WordDictionary(dictionaryFileName);
        pickedWord = dictionary.getRandomWord();
        printedWordAfterGuesses = new ArrayList<>();
        guessedLetters = new ArrayList<>();

        //Initialize the printedWordAfterGuesses array
        for (int i = 0; i < pickedWord.length(); i++) {
            printedWordAfterGuesses.add('_');
        }
    }

    // Main play logic function
    public boolean makePlayLogic(Character guess) {
        guess = Character.toUpperCase(guess);

        if (guessedLetters.contains(guess)) {
            observer.printGuessAlreadyMade(guess);
            // Ignore the play and return.
            return false;
        } else {
            guessedLetters.add(guess);

            if (pickedWord.toUpperCase().contains(guess.toString())) {
                // We have a correct guess!!
                updatePrintedWordAfterGuesses(guess);
                correctGuessCount++;

                observer.printCorrectGuess(guess);
                // Check if we are done!
                return checkIfGameOver();
            } else {
                // We have an incorrect guess, sadness
                observer.printIncorrectGuess(guess);
                return false;
            }
        }
    }

    // ----------------- Begin Helper Functions ----------------

    private void updatePrintedWordAfterGuesses(Character guess) {
        for (int i = 0; i < pickedWord.length(); i++) {
            if (pickedWord.toUpperCase().charAt(i) == guess) {
                printedWordAfterGuesses.set(i, pickedWord.charAt(i));
            }
        }
    }

    private boolean checkIfGameOver() {
        boolean hasUnderscoreStill = false;
        for (int i = 0; i < printedWordAfterGuesses.size(); i++) {
            if (Character.compare(printedWordAfterGuesses.get(i), '_') == 0) {
                // We found an underscore in the array
                hasUnderscoreStill = true;
            }
        }

        // If there are no underscores left, we are done, return true!
        return !hasUnderscoreStill;
    }

    private boolean isLetterInPickedWord(Character c) {
        for (int i = 0; i < pickedWord.length(); i++) {
            if (pickedWord.toUpperCase().charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    // ----------------- Begin Getter Functions -----------------

    public String getWordSoFar() {
        StringBuilder outputGuessedWord = new StringBuilder();
        for (int i = 0; i < printedWordAfterGuesses.size(); i++) {
            outputGuessedWord.append(printedWordAfterGuesses.get(i));
        }
        return outputGuessedWord.toString();
    }

    public String getBadGuessedLetters() {
        StringBuilder outputGuessedLettersString = new StringBuilder();
        for (int i = 0; i < guessedLetters.size(); i++) {
            if (!isLetterInPickedWord(guessedLetters.get(i))) {
                outputGuessedLettersString.append(guessedLetters.get(i));
                outputGuessedLettersString.append(" ");
            }
        }
        return outputGuessedLettersString.toString();
    }

    public String getPickedWord() {
        return pickedWord;
    }

    public int getGuessCount() {
        return guessedLetters.size();
    }

    public int getCorrectGuessCount() {
        return correctGuessCount;
    }

    public int getIncorrectGuessCount() {
        return (guessedLetters.size() - correctGuessCount);
    }

    public int getPickedWordLength() {
        return pickedWord.length();
    }

}
