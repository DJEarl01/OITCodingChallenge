public interface HangmanGameFrontEndObserver {
    public void printGuessAlreadyMade(Character guess);

    public void printCorrectGuess(Character guess);

    public void printIncorrectGuess(Character guess);
}
