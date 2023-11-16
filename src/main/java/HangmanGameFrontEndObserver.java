public interface HangmanGameFrontEndObserver {
    void printGuessAlreadyMade(Character guess);
    void printCorrectGuess(Character guess);
    void printIncorrectGuess(Character guess);
}
