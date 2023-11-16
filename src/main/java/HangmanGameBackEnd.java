import java.util.ArrayList;

//Logic class that handles the back end logic of the game.
public class HangmanGameBackEnd {
    private final String pickedWord;
    private final ArrayList<Character> guessedLetters;
    private final ArrayList<Character> printedWordAfterGuesses;
    private final HangmanGameFrontEndObserver observer;
    private int correctGuessCount;

    public HangmanGameBackEnd(HangmanGameFrontEndObserver observer, String dictionaryFileName) {
        WordDictionary dictionary = new WordDictionary(dictionaryFileName);

        pickedWord = dictionary.getRandomWord();
        this.observer = observer;
        correctGuessCount = 0;
        printedWordAfterGuesses = new ArrayList<>();
        guessedLetters = new ArrayList<>();

        //Initialize the printedWordAfterGuesses array
        for (int i = 0; i < pickedWord.length(); i++) {
            printedWordAfterGuesses.add('_');
        }
    }

    public boolean makePlayLogic(Character guess) {
        guess = Character.toUpperCase(guess);

        if (guessedLetters.contains(guess)) {
            // We've guessed this already.
            observer.printGuessAlreadyMade(guess);
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
        for (Character targetCharacter : printedWordAfterGuesses) {
            if (targetCharacter == '_') {
                // We found an underscore in the array
                hasUnderscoreStill = true;
                break;
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
        for (Character targetCharacter : printedWordAfterGuesses) {
            outputGuessedWord.append(targetCharacter);
        }
        return outputGuessedWord.toString();
    }

    public String getBadGuessedLetters() {
        StringBuilder outputGuessedLettersString = new StringBuilder();
        for (Character guessedLetter : guessedLetters) {
            if (!isLetterInPickedWord(guessedLetter)) {
                outputGuessedLettersString.append(guessedLetter);
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
